@MyFlight_Login
  Feature: Mercury Tours

    Scenario Outline: Login Mercury Exitoso

      Given Ingreso a la url "<caso_prueba>"
      When Ingreso el usuario "<caso_prueba>"
      And La contraseña "<caso_prueba>"
      Then se da clic en el boton Sing-IN ingresando correctamente


      Examples:
        | caso_prueba |
        |           1 |
