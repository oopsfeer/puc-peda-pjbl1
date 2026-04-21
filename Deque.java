/**
 * Classe Deque: implementacao usando vetor estatico circular.
 */
import java.io.Serializable;
class Deque implements Serializable {
   protected int N;                                // Guarda o tamanho maximo do deque.
   protected Object[] data;                        // Guarda os elementos do deque no vetor circular.
   protected int size;                             // Guarda a quantidade atual de elementos.
   protected int front;                            // Guarda o indice do inicio do deque.
   protected int rear;                             // Guarda o indice do fim do deque.
   protected int ptr;                              // Guarda o ponteiro usado para percorrer o deque.

   public Deque(int N) {
      // Recebe o tamanho maximo do deque e inicializa seus dados.
      this.N = N;                                 // Copia o tamanho maximo para o atributo da classe.
      data = new Object[N];                       // Cria o vetor que armazenara os elementos.
      size = 0;                                   // Define que o deque comeca vazio.
      front = 0;                                  // Define a posicao inicial do inicio do deque.
      rear = 0;                                   // Define a posicao inicial do fim do deque.
      ptr = 0;                                    // Define a posicao inicial do ponteiro de percurso.
   }

   public boolean isEmpty() {
      // Retorna verdadeiro quando o deque nao possui elementos.
      return size == 0;                           // Compara o tamanho atual com zero.
   }

   public boolean isFull() {
      // Retorna verdadeiro quando o deque atingiu a capacidade maxima.
      return size == N;                           // Compara a quantidade atual com a capacidade maxima.
   }

   public int getSize() {
      // Retorna a quantidade atual de elementos do deque.
      return size;                                // Devolve o valor armazenado em size.
   }

   public Object peekFront() {
      // Retorna o primeiro elemento sem remove-lo do deque.
      if (isEmpty())                              // Verifica se nao existe elemento no deque.
         return null;                             // Retorna null quando o deque esta vazio.
      return data[front];                         // Retorna o elemento localizado no inicio.
   }

   public Object peekRear() {
      // Retorna o ultimo elemento sem remove-lo do deque.
      if (isEmpty())                              // Verifica se nao existe elemento no deque.
         return null;                             // Retorna null quando o deque esta vazio.
      return data[rear];                          // Retorna o elemento localizado no fim.
   }

   public String toString() {
      // Monta e retorna uma string com os elementos do deque em ordem.
      if (isEmpty())                              // Verifica se o deque nao possui elementos.
         return "Deque vazio.";                   // Retorna mensagem apropriada para deque vazio.
      String lista = "";                          // Cria a string que acumulara os elementos.
      rewind();                                   // Posiciona o ponteiro de percurso no inicio.
      int strSize = 0;                            // Controla quantos elementos ja foram copiados.
      while (strSize < size) {                    // Repete enquanto faltarem elementos a copiar.
         lista += next().toString();              // Concatena o proximo elemento a string.
         strSize++;                               // Incrementa a quantidade de elementos copiados.
         if (strSize < size)                      // Verifica se ainda faltam elementos.
            lista += " ";                         // Acrescenta um espaco entre os elementos.
      }
      return lista;                               // Retorna a string montada com os elementos.
   }

   public void rewind() {
      // Coloca o ponteiro de percurso no inicio atual do deque.
      ptr = front;                                // Faz o ponteiro passar a apontar para o inicio.
   }

   public Object next() {
      // Retorna o elemento apontado e avanca o ponteiro no vetor circular.
      if (isEmpty())                              // Verifica se o deque nao possui elementos.
         return null;                             // Retorna null quando o deque esta vazio.
      Object e = data[ptr];                       // Guarda o elemento apontado atualmente.
      ptr++;                                      // avanca o ponteiro para a proxima posicao.
      if (ptr == N)                               // Verifica se o ponteiro ultrapassou o vetor.
         ptr = 0;                                 // Faz o ponteiro circular para a posicao zero.
      return e;                                   // Retorna o elemento que foi guardado.
   }

   public void addFirst(Object e) {
      // Pseudocodigo:
      // Verifique se o deque esta cheio.
      // Se estiver cheio, informe erro e encerre o programa.
      // Se estiver vazio, reposicione inicio e fim para o estado inicial.
      // Caso contrario, recue o ponteiro de inicio, circulando se preciso.
      // Insira o elemento na nova posicao de inicio.
      // Incremente o tamanho do deque.
   
      //********* COMPLETAR **********
   
      // Adiciona um novo elemento no inicio do deque.
      if (isFull()) {                             // Verifica se nao ha espaco para nova insercao.
         System.out.println("Erro: deque cheio ao adicionar no inicio.");
      // Exibe mensagem de erro ao usuario.
         System.exit(1);                  // Encerra o programa com codigo de erro.
      }
      if (isEmpty()) {                           // Verifica se o deque nao tem elementos.
         front = rear = 0;                       // Reposiciona inicio e fim para o estado inicial.
      } else {                                   // Executa quando ja existem elementos no deque.
         front--;                                // Recua o indice do inicio em uma posicao.
         if (front == -1)                        // Verifica se o indice saiu do vetor pela esquerda.
            front = N - 1;                       // Faz o indice circular para o fim do vetor.
      }
      data[front] = e;                           // Insere o elemento na nova posicao de inicio.
      size++;                                    // Incrementa a quantidade de elementos do deque.
   }

   public void addLast(Object e) {
      // Pseudocodigo:
      // Verifique se o deque esta cheio.
      // Se estiver cheio, informe erro e encerre o programa.
      // Se estiver vazio, reposicione inicio e fim para o estado inicial.
      // Caso contrario, avance o ponteiro de fim, circulando se preciso.
      // Insira o elemento na nova posicao de fim.
      // Incremente o tamanho do deque.
      
      //********* COMPLETAR **********
   
      // Adiciona um novo elemento no final do deque.
      if (isFull()) {                            // Verifica se nao ha espaco para nova insercao.
         System.out.println("Erro: deque cheio ao adicionar no fim.");
      // Exibe mensagem de erro ao usuario.
         System.exit(1);                  // Encerra o programa com codigo de erro.
      }
      if (isEmpty()) {                           // Verifica se o deque nao tem elementos.
         front = rear = 0;                       // Reposiciona inicio e fim para o estado inicial.    
      } else {                                   // Executa quando ja existem elementos no deque.
         rear++;                                 // avanca o indice do fim em uma posicao.
         if (rear == N)                          // Verifica se o indice saiu do vetor pela direita.
            rear = 0;                            // Faz o indice circular para a posicao zero.
      }
      data[rear] = e;                            // Insere o elemento na nova posicao de fim.      
      size++;                                    // Incrementa a quantidade de elementos do deque.
   }

   public Object deleteFirst() {
      // Pseudocodigo:
      // Verifique se o deque esta vazio.
      // Se estiver vazio, informe erro e encerre o programa.
      // Guarde o elemento do inicio.
      // Defina a posicao do inicio como null.
      // Decremente o tamanho do deque.
      // Se o deque ficou vazio, reposicione inicio e fim para o estado inicial.
      // Senao, avance o ponteiro de inicio, circulando se preciso. 
      // Retorne o elemento guardado.
   
      //********* COMPLETAR **********
   
      // Remove e retorna o elemento do inicio do deque.
      if (isEmpty()) {                            // Verifica se nao existe elemento para remover.
         System.out.println("Erro: deque vazio ao remover do inicio.");
      // Exibe mensagem de erro ao usuario.
         System.exit(1);                  // Encerra o programa com codigo de erro.
      }
      Object e_front = data[front];              // Guarda o elemento localizado no inicio.
      data[front] = null;                        // Define como null a posicao removida do vetor.
      size--;                                    // Decrementa a quantidade de elementos do deque.
      if (isEmpty())                             // Verifica se o deque ficou vazio.
         front = rear = 0;                       // Reposiciona inicio e fim para o estado inicial.      
      else {                                     // Se nao vazio.
         front++;                                // avanca o indice do inicio em uma posicao.
         if (front == N)                         // Verifica se o indice ultrapassou o vetor.
            front = 0;                           // Faz o indice circular para a posicao zero.   
      } 
      return e_front;                            // Retorna o elemento removido do inicio.
   }

   public Object deleteLast() {     
      // Pseudocodigo:
      // Verifique se o deque esta vazio.
      // Se estiver vazio, informe erro e encerre o programa.
      // Guarde o elemento do fim.
      // Defina a posicao do fim como null.
      // Decremente o tamanho do deque.
      // Se o deque ficou vazio, reposicione inicio e fim para o estado inicial.
      // Senao, recue o ponteiro de fim, circulando se preciso. 
      // Retorne o elemento guardado.     
   
      //********* COMPLETAR **********
   
      // Remove e retorna o elemento do fim do deque.
      if (isEmpty()) {                           // Verifica se nao existe elemento para remover.
         System.out.println("Erro: deque vazio ao remover do fim.");
      // Exibe mensagem de erro ao usuario.
         System.exit(1);                  // Encerra o programa com codigo de erro.
      }
      Object e_rear = data[rear];                // Guarda o elemento localizado no fim.
      data[rear] = null;                         // Define como null a posicao removida do vetor.
      size--;                                    // Decrementa a quantidade de elementos do deque.   
      if (isEmpty())                             // Verifica se o deque ficou vazio.
         front = rear = 0;                       // Reposiciona inicio e fim para o estado inicial.      
      else {                                     // Se nao vazio.
         rear--;                                 // Recua o indice do fim em uma posicao.
         if (rear == -1)                         // Verifica se o indice saiu do vetor pela esquerda.
            rear = N - 1;                        // Faz o indice circular para o fim do vetor.  
      }      
      return e_rear;                             // Retorna o elemento removido do fim.
   }
}
