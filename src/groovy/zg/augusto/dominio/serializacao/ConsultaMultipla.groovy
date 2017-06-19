package zg.augusto.dominio.serializacao

import groovy.transform.Canonical

@Canonical
class ConsultaMultipla <T> {

    List<T> resultados
    Long quantidade
    String ordernadoPor
    Long maximo
    Long offset

}
