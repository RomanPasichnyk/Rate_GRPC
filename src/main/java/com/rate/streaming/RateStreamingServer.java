package com.rate.streaming;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.NettyServerBuilder;

import java.io.IOException;

public class RateStreamingServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        RateUSDStreamingService usdService = new RateUSDStreamingService();
        RateEuroStreamingService euroService = new RateEuroStreamingService();
        RateRubStreamingService rubService = new RateRubStreamingService();

        RateStreamingService rateService = new RateStreamingService(usdService, euroService, rubService);
        Server rateServer = NettyServerBuilder.forPort(8090).addService(rateService).build().start();

        Runtime.getRuntime().addShutdownHook(new Thread(rateServer::shutdownNow));

        rateServer.awaitTermination();

    }

}
