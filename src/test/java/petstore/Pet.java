package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Pet {
    //Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço da entidade do pet





    //Métodos e Funções

    //-- função ler json
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //-- incluir create post
    @Test  // identifica o método como um teste para o testng
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        given()         //Dado
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()          //Quando
                .post(uri)
        .then()         //Então
                .log().all()
                .statusCode(200)
        ;

    }

}
