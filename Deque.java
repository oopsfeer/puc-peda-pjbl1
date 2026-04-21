/**
 * Classe Deque: implementação usando vetor estático circular.
 */
import java.io.Serializable;
class Deque implements Serializable {
   protected int N;          // Guarda o tamanho máximo do deque.
   protected Object[] data;  // Guarda os elementos do deque no vetor circular.
   protected int size;       // Guarda a quantidade atual de elementos.
   protected int front;      // Guarda o índice do início do deque.
   protected int rear;       // Guarda o índice do fim do deque.
   protected int ptr;        // Guarda o ponteiro usado para percorrer o deque.

   public Deque(int N) {
      // Recebe o tamanho máximo do deque e inicializa seus dados.
      this.N = N;           // Copia o tamanho máximo para o atributo da classe.
      data = new Object[N]; // Cria o vetor que armazenará os elementos.
      size = 0;             // Define que o deque começa vazio.
      front = 0;            // Define a posição inicial do início do deque.
      rear = 0;             // Define a posição inicial do fim do deque.
      ptr = 0;              // Define a posição inicial do ponteiro de percurso.
   }

   public boolean isEmpty() {
      // Retorna verdadeiro quando o deque não possui elementos.
      return size == 0;    // Compara o tamanho atual com zero.
   }

   public boolean isFull() {
      // Retorna verdadeiro quando o deque atingiu a capacidade máxima.
      return size == N;    // Compara a quantidade atual com a capacidade máxima.
   }

   public int getSize() {
      // Retorna a quantidade atual de elementos do deque.
      return size;         // Devolve o valor armazenado em size.
   }

   public Object peekFront() {
      // Retorna o primeiro elemento sem removê-lo do deque.
      if (isEmpty())       // Verifica se não existe elemento no deque.
         return null;      // Retorna null quando o deque está vazio.
      return data[front];  // Retorna o elemento localizado no início.
   }

   public Object peekRear() {
      // Retorna o último elemento sem removê-lo do deque.
      if (isEmpty())       // Verifica se não existe elemento no deque.
         return null;      // Retorna null quando o deque está vazio.
      return data[rear];   // Retorna o elemento localizado no fim.
   }

   public String toString() {
      // Monta e retorna uma string com os elementos do deque em ordem.
      if (isEmpty())       // Verifica se o deque não possui elementos.
         return "Deque vazio."; // Retorna mensagem apropriada para deque vazio.
      String lista = "";   // Cria a string que acumulará os elementos.
      rewind();            // Posiciona o ponteiro de percurso no início.
      int strSize = 0;     // Controla quantos elementos já foram copiados.
      while (strSize < size) { // Repete enquanto faltarem elementos a copiar.
         lista += next().toString(); // Concatena o próximo elemento à string.
         strSize++;        // Incrementa a quantidade de elementos copiados.
         if (strSize < size) // Verifica se ainda faltam elementos.
            lista += " ";  // Acrescenta um espaço entre os elementos.
      }
      return lista;        // Retorna a string montada com os elementos.
   }

   public void rewind() {
      // Coloca o ponteiro de percurso no início atual do deque.
      ptr = front;         // Faz o ponteiro passar a apontar para o início.
   }

   public Object next() {
      // Retorna o elemento apontado e avança o ponteiro no vetor circular.
      if (isEmpty())       // Verifica se o deque não possui elementos.
         return null;      // Retorna null quando o deque está vazio.
      Object e = data[ptr]; // Guarda o elemento apontado atualmente.
      ptr++;               // Avança o ponteiro para a próxima posição.
      if (ptr == N)        // Verifica se o ponteiro ultrapassou o vetor.
         ptr = 0;          // Faz o ponteiro circular para a posição zero.
      return e;            // Retorna o elemento que foi guardado.
   }

   public void addFirst(Object e) {
      // Pseudocódigo:
      // Verifique se o deque está cheio.
      // Se estiver cheio, informe erro e encerre o programa.
      // Se estiver vazio, reposicione início e fim para o estado inicial.
      // Caso contrário, recue o ponteiro de início, circulando se preciso.
      // Insira o elemento na nova posição de início.
      // Incremente o tamanho do deque.
   
      //********* COMPLETAR **********
   
      // Adiciona um novo elemento no início do deque.
      if (isFull()) {      // Verifica se não há espaço para nova inserção.
         System.out.println("Erro: deque cheio ao adicionar no início.");
                           // Exibe mensagem de erro ao usuário.
         System.exit(1);   // Encerra o programa com código de erro.
      }
      if (isEmpty()) {     // Verifica se o deque não tem elementos.
         front = rear = 0; // Reposiciona início e fim para o estado inicial.
      } else {            // Executa quando já existem elementos no deque.
         front--;            // Recua o índice do início em uma posição.
         if (front == -1)    // Verifica se o índice saiu do vetor pela esquerda.
            front = N - 1;   // Faz o índice circular para o fim do vetor.
      }
      data[front] = e; // Insere o elemento na nova posição de início.
      size++;          // Incrementa a quantidade de elementos do deque.
   }

   public void addLast(Object e) {
      // Pseudocódigo:
      // Verifique se o deque está cheio.
      // Se estiver cheio, informe erro e encerre o programa.
      // Se estiver vazio, reposicione início e fim para o estado inicial.
      // Caso contrário, avance o ponteiro de fim, circulando se preciso.
      // Insira o elemento na nova posição de fim.
      // Incremente o tamanho do deque.
      
      //********* COMPLETAR **********
   
      // Adiciona um novo elemento no final do deque.
      if (isFull()) {      // Verifica se não há espaço para nova inserção.
         System.out.println("Erro: deque cheio ao adicionar no fim.");
                           // Exibe mensagem de erro ao usuário.
         System.exit(1);   // Encerra o programa com código de erro.
      }
      if (isEmpty()) {      // Verifica se o deque não tem elementos.
         front = rear = 0;    // Reposiciona início e fim para o estado inicial.    
      } else {             // Executa quando já existem elementos no deque.
         rear++;             // Avança o índice do fim em uma posição.
         if (rear == N)      // Verifica se o índice saiu do vetor pela direita.
            rear = 0;        // Faz o índice circular para a posição zero.
      }
      data[rear] = e; // Insere o elemento na nova posição de fim.      
      size++;        // Incrementa a quantidade de elementos do deque.
   }

   public Object deleteFirst() {
      // Pseudocódigo:
      // Verifique se o deque está vazio.
      // Se estiver vazio, informe erro e encerre o programa.
      // Guarde o elemento do início.
      // Defina a posição do início como null.
      // Decremente o tamanho do deque.
      // Se o deque ficou vazio, reposicione início e fim para o estado inicial.
      // Senão, avance o ponteiro de início, circulando se preciso. 
      // Retorne o elemento guardado.
   
      //********* COMPLETAR **********
   
      // Remove e retorna o elemento do início do deque.
      if (isEmpty()) {     // Verifica se não existe elemento para remover.
         System.out.println("Erro: deque vazio ao remover do início.");
                           // Exibe mensagem de erro ao usuário.
         System.exit(1);   // Encerra o programa com código de erro.
      }
      Object e_front = data[front]; // Guarda o elemento localizado no início.
      data[front] = null;  // Define como null a posição removida do vetor.
      size--;              // Decrementa a quantidade de elementos do deque.
      if (isEmpty())       // Verifica se o deque ficou vazio.
         front = rear = 0;    // Reposiciona início e fim para o estado inicial.      
      else {               // Se não vazio.
         front++;             // Avança o índice do início em uma posição.
         if (front == N)      // Verifica se o índice ultrapassou o vetor.
            front = 0;           // Faz o índice circular para a posição zero.   
      } 
      return e_front;      // Retorna o elemento removido do início.
   }

   public Object deleteLast() {     
      // Pseudocódigo:
      // Verifique se o deque está vazio.
      // Se estiver vazio, informe erro e encerre o programa.
      // Guarde o elemento do fim.
      // Defina a posição do fim como null.
      // Decremente o tamanho do deque.
      // Se o deque ficou vazio, reposicione início e fim para o estado inicial.
      // Senão, recue o ponteiro de fim, circulando se preciso. 
      // Retorne o elemento guardado.     
   
      //********* COMPLETAR **********
   
      // Remove e retorna o elemento do fim do deque.
      if (isEmpty()) {     // Verifica se não existe elemento para remover.
         System.out.println("Erro: deque vazio ao remover do fim.");
                           // Exibe mensagem de erro ao usuário.
         System.exit(1);   // Encerra o programa com código de erro.
      }
      Object e_rear = data[rear]; // Guarda o elemento localizado no fim.
      data[rear] = null;  // Define como null a posição removida do vetor.
      size--;              // Decrementa a quantidade de elementos do deque.   
      if (isEmpty())       // Verifica se o deque ficou vazio.
         front = rear = 0;    // Reposiciona início e fim para o estado inicial.      
      else {               // Se não vazio.
         rear--;              // Recua o índice do fim em uma posição.
         if (rear == -1)      // Verifica se o índice saiu do vetor pela esquerda.
            rear = N - 1;        // Faz o índice circular para o fim do vetor.  
      }      
      return e_rear;       // Retorna o elemento removido do fim.
   }
}