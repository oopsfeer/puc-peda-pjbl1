import java.io.*;

public class ComposicaoFerroviaria extends Deque implements Serializable {
    ObjetoPersistente ArqComp;
    public ComposicaoFerroviaria(int N, String nomeArquivo) {
    super(N);                                                               // Chama o construtor de Deque.
    ArqComp = new ObjetoPersistente(nomeArquivo);
    carregar();                                                             // Carrega arquivo da composição ferroviária se existir.
    }

    private void salvar() {                                                 // Salva a composição ferroviária em arquivo.
    ArqComp.salvar(this);
    }

    private void carregar() {
    ComposicaoFerroviaria cf = (ComposicaoFerroviaria) ArqComp.carregar();
    
    if (!(cf==null)) {                                                      // Se o objeto foi recuperado, atualiza composição ferroviária.
                                                                            // É necessário redefinir os atributos do "deque pai" com os dados da
                                                                            // composição carregada.
    this.front = cf.front;
    this.rear = cf.rear;
    this.ptr = cf.ptr;
    this.size = cf.size;
    this.N = cf.N;
    this.data = cf.data;
    }

    }

}