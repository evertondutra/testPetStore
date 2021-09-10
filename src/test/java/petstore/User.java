package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class User {
    String uri = "https://petstore.swagger.io/v2/user";

    //ler Json
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir usuario
    @Test
    public void incluirUsuario() throws IOException {
        String jsonBody = lerJson("src/test/resources/usuarios/user2.json");


        given()         //Dado
                .contentType("application/json")  // tipo de dado que vai usar
                .log().all()         //aqui vai logar tudo
                .body(jsonBody)      // insere o corpo do json
        .when()          //Quando
                .post(uri)       // aqui chama o endereço e passa os dados do body
        .then()         //Então
                .log().all()
                .statusCode(200)
                //validação
                .body("code", is(200))
                .body("type", is("unknown"))

        ;

    }

    @Test
    public void consultarUsuario(){
        String userUsername = "Tonex";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + userUsername)
        .then()
                .log().all()
                .statusCode(200)
                .body("username", is("Tonex"))
                .body("firstName",is("Everton"))
                .body("email",is("tone@teste.com"))
        ;
    }


}
