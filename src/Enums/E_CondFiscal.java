package Enums;


/**
 * ENUM que tiene los diferentes tipos de monoTributo que puede elegir UsuarioVenta.
 */
public enum E_CondFiscal {

    MONOTRIBUTO_A('A'),MONOTRIBUTO_B('B'),MONOTRIBUTO_C('C'),MONOTRIBUTO_D('D'),MONOTRIBUTO_E('E'),MONOTRIBUTO_F('F'),MONOTRIBUTO_G('G');


    private char clase;

    E_CondFiscal(char clase) {
        this.clase = clase;
    }
}
