package strategos.networking.networks;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import strategos.SaveInstance;
import strategos.networking.Network;
import strategos.networking.handlers.DataHandler;

public class Client implements Network {
	private String host;
	private int port;
	private NetworkHandler clientHandler;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
		clientHandler = new NetworkHandler();
	}

	@Override
	public void run() throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws InterruptedException {
					ch.pipeline().addLast(new DataHandler(), clientHandler);
				}
			});

			// Start the client.
			ChannelFuture f = b.connect(host, port).sync();

			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

	@Override
	public void send(SaveInstance instance) throws InterruptedException {
		clientHandler.send(instance);
	}
}