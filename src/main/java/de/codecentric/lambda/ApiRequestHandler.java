package de.codecentric.lambda;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import de.codecentric.ApiWrapperLambdaModule;
import de.codecentric.soap.SoapWrapper;

public class ApiRequestHandler implements RequestHandler<SoapWrapper, SoapWrapper> {

    private static final Injector INJECTOR = Guice.createInjector(new ApiWrapperLambdaModule());

    private final LambdaRequestProcessor lambdaRequestProcessor = INJECTOR.getInstance(LambdaRequestProcessor.class);

    @Override
    public SoapWrapper handleRequest(SoapWrapper request, Context context) {
        return lambdaRequestProcessor.process(request);
    }
}
