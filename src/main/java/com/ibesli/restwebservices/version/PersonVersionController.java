package com.ibesli.restwebservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController
{
    @GetMapping("v1/person")
    public PersonV1 personV1()
    {
        return new PersonV1("ilyas");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2()
    {
        return new PersonV2(new Name("ilyas", "besli"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1()
    {
        return new PersonV1("ilyas");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2()
    {
        return new PersonV2(new Name("ilyas", "besli"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSON=1")
    public PersonV1 headerV1()
    {
        return new PersonV1("ilyas");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSON=2")
    public PersonV2 headerV2()
    {
        return new PersonV2(new Name("ilyas", "besli"));
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1()
    {
        return new PersonV1("ilyas");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2()
    {
        return new PersonV2(new Name("ilyas", "besli"));
    }

}
