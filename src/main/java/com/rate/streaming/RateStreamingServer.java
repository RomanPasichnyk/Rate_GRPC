package com.rate.streaming;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.NettyServerBuilder;

import java.io.IOException;

public class RateStreamingServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        UkrsibStreamingService ukrSibRateStreamingService = new UkrsibStreamingService();
        RateStreamingService rateService = new RateStreamingService(ukrSibRateStreamingService);
        Server rateServer = NettyServerBuilder.forPort(8090).addService(rateService).build().start();

        Runtime.getRuntime().addShutdownHook(new Thread(rateServer::shutdownNow));

        rateServer.awaitTermination();

    }

}
