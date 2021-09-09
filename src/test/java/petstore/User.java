package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class User {
    String uri = "https://petstore.swagger.io/v2/user";

    //ler Json
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir usuario
    @Test
    public void incluirUsuario() throws IOException {
        String jsonBody = lerJson("src/test/resources/usuarios/user1.json");


        given()
                .contentType("application/json").log().all().body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200);

    }


}
