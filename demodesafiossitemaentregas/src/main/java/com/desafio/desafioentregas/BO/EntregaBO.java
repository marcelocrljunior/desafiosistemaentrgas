package com.desafio.desafioentregas.BO;

import com.desafio.desafioentregas.DAO.MalhaDAO;
import com.desafio.desafioentregas.models.*;

import java.util.*;

/**
 * Classe que representa a camada de negócio dos objetos de Entrega
 * @author marceloc.r.lopesjunior
 */
public class EntregaBO {

    private MalhaDAO malhadao = new MalhaDAO();

    // Lista que guarda o melhor caminho
    List<No> melhorcaminho = new ArrayList<No>();

    No nocaminho = new No();

    // Variavel que guarda o nó atual
    No noatual = new No();

    // Variavel que marca o nó vizinho do nó atual
    No novizinho = new No();

    // Lista dos nós que ainda nao foram utilizados
    List<No> naoVisitados = new ArrayList<No>();

    //Mapper utilizado para montar os Nós e deixa-los unicos na ideia de um grafo.
    Map<String, No> mapa = new HashMap<String, No>();

    /**
     * Método que encontra o melhor caminho utilizadno o algoritmo de dijkistra.
     * @param entregaRequest
     * @return
     * @throws Exception
     */
    public EntregaResponse getMelhorCaminho(EntregaRequest entregaRequest) throws Exception
    {
        EntregaResponse retorno = new EntregaResponse();
        List<Malha> malhas = malhadao.getAll();
        Grafo grafo = montarGrafo(malhas);
        retorno.setRota(executarDijkstra(grafo,entregaRequest));
        retorno.setCusto(calcularCusto(entregaRequest.getAutonomia(),entregaRequest.getValordolitro()));
        return retorno;

    }

    private String executarDijkstra(Grafo grafo,EntregaRequest entregaRequest)
    {
        No origem = grafo.encontrarNo(entregaRequest.getOrigem());
        No destino = grafo.encontrarNo(entregaRequest.getDestino());


        //Coloca o nó origem no melhor caminho.
        melhorcaminho.add(origem);

        //Inicializando as distancias
        for (int i = 0; i < grafo.getNos().size(); i++) {

            // Seguindo a lógica do algoritmo no nó atual a distancia é zero, e os outros infinita.
            // representado por um "max value" 9999.
            if (grafo.getNos().get(i).getDescricao()
                    .equals(origem.getDescricao())) {

                grafo.getNos().get(i).setDistancia(0);

            } else {

                grafo.getNos().get(i).setDistancia(9999);

            }
            // Adiciona os nos a lista de nao utilizados
            this.naoVisitados.add(grafo.getNos().get(i));
        }

        Collections.sort(naoVisitados);

        // O algoritmo fica em um loop até que todos os nos sejam utilizados
        while (!this.naoVisitados.isEmpty()) {

            // Seguindo a lógica do algoritmo desempilha da lista sempre o primeiro nó
            // pois é o que possui a menor distancia.

            noatual = this.naoVisitados.get(0);

            /*
             * Para cada nó calcula a possivel distancia dos seus vizinhos
             * Somando a distancia do nó atual com a da malha
             * correspondente. Se essa distancia for menor que a distancia do
             * vizinho, ou seja a melhor a opção, a distancia entao é atualizada.
             */
            for (int i = 0; i < noatual.getMalhas().size(); i++) {

                novizinho = noatual.getMalhas().get(i).getNodestino();
                if (!novizinho.verificarVisita()) {

                    // Comparando a distância do vizinho com a possível distância
                    if (novizinho.getDistancia() > (noatual.getDistancia() + noatual
                            .getMalhas().get(i).getDistancia())) {

                        novizinho.setDistancia(noatual.getDistancia()
                                + noatual.getMalhas().get(i).getDistancia());
                        novizinho.setPai(noatual);

                        /*
                         * Se o vizinho é o nó com o menor custo (menor distancia), e foi feita uma
                         * mudanca na distancia, a lista com o menor caminho é atualizada pois existe um melhor caminho

                         */
                        if (novizinho == destino) {
                            melhorcaminho.clear();
                            nocaminho = novizinho;
                            melhorcaminho.add(novizinho);
                            while (nocaminho.getPai() != null) {

                                melhorcaminho.add(nocaminho.getPai());
                                nocaminho = nocaminho.getPai();

                            }
                            //Ordena a lista para exibir corretamente o caminho origem ao destino
                            Collections.sort(melhorcaminho);

                        }
                    }
                }

            }

            noatual.visitar();
            this.naoVisitados.remove(noatual);

            Collections.sort(naoVisitados);

        }

        String retorno = new String();
        for(No no: melhorcaminho)
        {
            retorno += no.getDescricao();
        }

        return retorno;

    }

    private Grafo montarGrafo(List<Malha> malhas)
    {
        Grafo retorno = new Grafo();
        No n;
        for(Malha malha : malhas){

            n = (No) mapa.get(malha.getOrigem());
            if (n == null)
                n = new No();


            n.setDescricao(malha.getOrigem());
            malha.setNoorigem(n);

            //atualiza ou cria um nó no mapa
            mapa.put(malha.getOrigem(), n);

            if(n.getVizinhos() == null)
                n.setVizinhos(new ArrayList<No>());

            No vizinho = mapa.get(malha.getDestino());
            if (vizinho == null)
                vizinho = new No();

            vizinho.setDescricao(malha.getDestino());
            malha.setNodestino(vizinho);
            mapa.put(malha.getDestino(), vizinho);
            n.getVizinhos().add(vizinho);
            n.setDistancia(malha.getDistancia());
            if(n.getMalhas() == null)
                n.setMalhas(new ArrayList<Malha>());

            n.getMalhas().add(malha);
            retorno.adicionarNo(n);
        }

        return retorno;

    }

    private double calcularCusto(double autonomia, double valorcombustivel)
    {

        //O ultimo nó ja tem a dsitancia que foi percorrida
        double distanciatotalpercorrida = melhorcaminho.get(melhorcaminho.size() - 1).getDistancia();

        double quantidadelistrosgastos = distanciatotalpercorrida / autonomia;
        double custodoslitros = quantidadelistrosgastos* valorcombustivel;

        return custodoslitros;
    }

}
