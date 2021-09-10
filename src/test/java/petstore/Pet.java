package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;


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
//--- estrutura do resassure ---

        given()         //Dado
                .contentType("application/json")  // tipo de dado que vai usar
                .log().all()         //aqui vai logar tudo
                .body(jsonBody)      // insere o corpo do json
        .when()          //Quando
                .post(uri)       // aqui chama o endereço e passa os dados do body
        .then()         //Então
                .log().all()    //aqui mostra o retorno do que aconteceu
                .statusCode(200)    // aqui mostra o status code 200
                .body("name", is("Kiara"))
                .body("status", is("available"))
                .body("category.name", is("dog"))
                .body("tags.name", contains("sta"))

        ;

    }

}
