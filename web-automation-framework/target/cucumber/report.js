$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("MyFlight_Login.feature");
formatter.feature({
  "line": 1,
  "name": "Mercury Tours",
  "description": "",
  "id": "mercury-tours",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Login Mercury Exitoso",
  "description": "",
  "id": "mercury-tours;login-mercury-exitoso",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@MyFlight_Login_Existoso"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Ingreso a la url \"\u003ccaso_prueba\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Ingreso el usuario \"\u003ccaso_prueba\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "La contraseña \"\u003ccaso_prueba\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "se da clic en el boton Sing-IN ingresando correctamente",
  "keyword": "Then "
});
formatter.examples({
  "line": 13,
  "name": "",
  "description": "",
  "id": "mercury-tours;login-mercury-exitoso;",
  "rows": [
    {
      "cells": [
        "caso_prueba"
      ],
      "line": 14,
      "id": "mercury-tours;login-mercury-exitoso;;1"
    },
    {
      "cells": [
        "1"
      ],
      "line": 15,
      "id": "mercury-tours;login-mercury-exitoso;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 255260900,
  "status": "passed"
});
formatter.before({
  "duration": 11302870700,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Login Mercury Exitoso",
  "description": "",
  "id": "mercury-tours;login-mercury-exitoso;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@MyFlight_Login_Existoso"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Ingreso a la url \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Ingreso el usuario \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "La contraseña \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "se da clic en el boton Sing-IN ingresando correctamente",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 18
    }
  ],
  "location": "MyFlight_Login.ingresoALaUrl(String)"
});
formatter.result({
  "duration": 9502258300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 20
    }
  ],
  "location": "MyFlight_Login.ingresoElUsuario(String)"
});
formatter.result({
  "duration": 1272168900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 15
    }
  ],
  "location": "MyFlight_Login.laContraseña(String)"
});
formatter.result({
  "duration": 1163566400,
  "status": "passed"
});
formatter.match({
  "location": "MyFlight_Login.seDaClicEnElBotonSingINIngresandoCorrectamente()"
});
formatter.result({
  "duration": 10392082700,
  "status": "passed"
});
formatter.after({
  "duration": 1349275800,
  "status": "passed"
});
});