package com.amsidh.mvc.springbootkarateintegration.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(Karate.class)
@CucumberOptions(features = {"classpath:karate"})
class PersonControllerTestIT {
    private static final int PORT_NUMBER = 8989;

    private static final WireMockServer wireMockServer
            = new WireMockServer(WireMockConfiguration.wireMockConfig().port(PORT_NUMBER));

    @BeforeClass
    public static void setUp() {
        wireMockServer.start();
        configureFor("localhost", PORT_NUMBER);

        //http://localhost:8989/persons
        stubFor(
                get(urlEqualTo("/persons"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("[{id:\"1\", name:\"Amsidh Lokhande\", email:\"amsidh@gmail.com\"},{id:\"2\", name:\"Viru Lokhande\", email:\"viru@gmail.com\"},{id:\"3\", name:\"Adithi Lokhande\", email:\"adithi@gmail.com\"}]")));


        //http://localhost:8989/persons/1
        stubFor(
                get(urlEqualTo("/persons/1"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{id:\"1\", name:\"Amsidh Lokhande\", email:\"amsidh@gmail.com\"}")));

        //http://localhost:8989/persons/10
        stubFor(
                get(urlEqualTo("/persons/10"))
                        .willReturn(aResponse()
                                .withStatus(404)
                                .withBody("Person with personId 10 not found.")));


        //http://localhost:8989/persons post method
        stubFor(
                post(urlEqualTo("/persons"))
                        .withHeader("content-type", equalTo("application/json"))
                        .withRequestBody(containing("{\"id\":\"5\",\"name\":\"Suresh Rupnar\",\"email\":\"suresh@gmail.com\"}"))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"id\":\"5\",\"name\":\"Suresh Rupnar\",\"email\":\"suresh@gmail.com\"}")));

        //http://localhost:8989/persons post method
        stubFor(
                post(urlEqualTo("/persons"))
                        .withHeader("content-type", equalTo("application/json"))
                        .withRequestBody(containing("{\"id\":\"6\",\"name\":\"Viru Lokhande\",\"email\":\"viru@gmail.com\"}"))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"id\":\"6\",\"name\":\"Viru Lokhande\",\"email\":\"viru@gmail.com\"}")));


        //http://localhost:8989/persons put method
        stubFor(
                put(urlEqualTo("/persons/2"))
                        .withHeader("content-type", equalTo("application/json"))
                        .withRequestBody(containing("name"))
                        .withRequestBody(containing("email"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{id:\"2\",name:\"Viru Babasha Lokhande\",email:\"virulokhande@gmail.com\"}")));

        //http://localhost:8989/hello/Amsidh
        stubFor(
                get(urlEqualTo("/hello/Amsidh"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("Hello! Amsidh, How are you?")));


        //http://localhost:8989/hello/bad
        stubFor(
                get(urlEqualTo("/hello/bad"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("Custom Bad request exception thrown")));

        //http://localhost:8989/hello/bad
        stubFor(
                get(urlEqualTo("/hello/notfound"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("Custom NotFound request exception thrown")));

        //http://localhost:8989/hello/forbidden
        stubFor(
                get(urlEqualTo("/hello/forbidden"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("To show an example of a custom message")));

        //http://localhost:8989/hello/forbidden
        stubFor(
                get(urlEqualTo("/hello/dummy"))
                        .willReturn(aResponse()
                                .withStatus(404)));
    }


    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }
}