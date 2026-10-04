package com.example.corporate.soap;

import com.example.corporate.service.UserService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

@Endpoint
public class UserSoapEndpoint {
    private static final String NS = "http://example.com/corporate/users";
    private final UserService service;

    public UserSoapEndpoint(UserService service) { this.service = service; }

    @PayloadRoot(namespace = NS, localPart = "getUserCountRequest")
    @ResponsePayload
    public Source count(@RequestPayload Source ignored) throws Exception {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElementNS(NS, "getUserCountResponse");
        Element count = document.createElementNS(NS, "count");
        count.setTextContent(String.valueOf(service.all().size()));
        root.appendChild(count);
        document.appendChild(root);
        return new DOMSource(document);
    }
}
