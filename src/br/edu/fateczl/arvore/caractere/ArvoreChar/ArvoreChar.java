package br.edu.fateczl.arvore.caractere.ArvoreChar;

public class ArvoreChar {
    
    No raiz;
    
    // Inicializando a Árvore
    public ArvoreChar() {
        raiz = null;
    }
    
    // Insert - Inserção
    private void insert(No no, No raizSubArvore) {
        if (raiz == null) { // Se for nula, meu No é a raiz!
            raiz = no;
        } else {
            if (no.dado < raizSubArvore.dado) { // Caminharei pela Esquerda
                if (raizSubArvore.esquerda == null) {
                    raizSubArvore.esquerda = no;
                } else {
                    insert(no, raizSubArvore.esquerda);
                }
            } else { // Caminharei pela Direita
                if (raizSubArvore.direita == null) {
                    raizSubArvore.direita = no;
                } else {
                    insert(no, raizSubArvore.direita);
                }
            }
        }
    }
    
    public void insertLeaf(char dado) {
        No no = new No();
        no.dado = dado;
        no.esquerda = null;
        no.direita = null;
        insert(no, raiz);
    }
    
    // Atravessamentos - Pré-Fixo (Pré-Ordem), In-Fixo (Em Ordem) e Pós-Fixo (Pós-Ordem)
    
    // Prefix
    private void prefix(No raizSubArvore) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia"); 
        } else {
            System.out.print(raizSubArvore.dado + " ");
            if (raizSubArvore.esquerda != null) {
                prefix(raizSubArvore.esquerda);
            }
            if (raizSubArvore.direita != null) {
                prefix(raizSubArvore.direita);
            }
        }
    }
    
    // Infix
    private void infix(No raizSubArvore) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia"); 
        } else {
            if (raizSubArvore.esquerda != null) {
                infix(raizSubArvore.esquerda);
            }
            System.out.print(raizSubArvore.dado + " ");
            if (raizSubArvore.direita != null) {
                infix(raizSubArvore.direita);
            }
        }
    }
    
    // Postfix
    private void postfix(No raizSubArvore) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia"); 
        } else {
            if (raizSubArvore.esquerda != null) {
                postfix(raizSubArvore.esquerda);
            }
            if (raizSubArvore.direita != null) {
                postfix(raizSubArvore.direita);
            }
            System.out.print(raizSubArvore.dado + " ");
        }
    }
    
    public void prefixSearch() throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        }
        prefix(raiz);    
    }
    
    public void infixSearch() throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        }
        infix(raiz);    
    }
    
    public void postfixSearch() throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        }
        postfix(raiz);    
    }
    
    // Busca / Search
    private No nodeSearch(No raizSubArvore, char valor) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else if (valor < raizSubArvore.dado) {
            return nodeSearch(raizSubArvore.esquerda, valor);
        } else if (valor > raizSubArvore.dado) {
            return nodeSearch(raizSubArvore.direita, valor);
        } else {
            return raizSubArvore;
        }
    }
    
    // Nível da Árvore
    private int nodeLevel(No raizSubArvore, char valor) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else if (valor < raizSubArvore.dado) {
            return 1 + nodeLevel(raizSubArvore.esquerda, valor);
        } else if (valor > raizSubArvore.dado) {
            return 1 + nodeLevel(raizSubArvore.direita, valor);
        } else {
            return 0;
        }
    }
    
    public void search(char valor) {
        try {
            No no = nodeSearch(raiz, valor);
            int level = nodeLevel(raiz, valor);
            System.out.println("Dado " + no.dado + " no nível " + level);
        } catch (Exception e) {
            System.err.println("Dado não encontrado");
        }
    }
    
    private No remove(No raizSubArvore, char valor) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else if (valor < raizSubArvore.dado) {
            raizSubArvore.esquerda = remove(raizSubArvore.esquerda, valor);
        } else if (valor > raizSubArvore.dado) {
            raizSubArvore.direita = remove(raizSubArvore.direita, valor);
        } else {
            if (raizSubArvore.esquerda == null && raizSubArvore.direita == null) {
                raizSubArvore = null;
            } else if (raizSubArvore.esquerda == null) {
                raizSubArvore = raizSubArvore.direita;
            } else if (raizSubArvore.direita == null) {
                raizSubArvore = raizSubArvore.esquerda;
            } else {
                No no = raizSubArvore.esquerda;
                while (no.direita != null) {
                    no = no.direita;
                }
                raizSubArvore.dado = no.dado;
                no.dado = valor;
                raizSubArvore.esquerda = remove(raizSubArvore.esquerda, valor);
            }
        }
        return raizSubArvore;
    }
    
    public void removeChild(char valor) throws Exception {
        raiz = remove(raiz, valor);
    }
}

