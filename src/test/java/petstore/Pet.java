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
    @Test(priority = 1)  // identifica o método como um teste para o testng
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
                .body("category.name", is("AX2345LORT"))
                .body("tags.name", contains("data"))
        ;
    }

    @Test(priority = 2)
    public void consultarPet(){
        String petId = "20022020";

        String token =

        //Requisição
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petId) // aqui faz a chamada , a requisição nesse endereço
        .then()
                .log().all()
                .statusCode(200)
                .body("category.name", is("AX2345LORT"))
                .body("name", is("Kiara"))
                .body("status", is("available"))
                .body("tags.id", contains(20210812))
        .extract()
                .path("category.name")
        ;

        System.out.println("O token é  " + token);
    }

    @Test(priority = 3)
    public void alterarPet() throws IOException {
        String jsonBody = lerJson("db/pet2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Kiara"))
                .body("status", is("sold"))
        ;
    }

    @Test(priority = 4)
    public void excluirPet(){
        String petId = "20022020";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is((petId)))
        ;

    }

}
