package robo;
import java.util.*;

public class Plano {
	private int tamanhoX;
    private int tamanhoY;
    private List<Robo> robos;
    private List<Aluno> alunos;
    private List<Bug> bugs;
    private int alunosResgatados;
    private int bugsEncontrados;
   

    public Plano(int tamanhoX, int tamanhoY) {
        this.tamanhoX = tamanhoX;
        this.tamanhoY = tamanhoY;
        this.robos = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.bugs = new ArrayList<>();
        this.alunosResgatados = 0;
        this.bugsEncontrados = 0;
    }

    public void adicionarRobo(Robo robo) {
        robos.add(robo);
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void adicionarBug(Bug bug) {
        bugs.add(bug);
    }

    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o seu nome: ");
        String nomeJogador = scanner.nextLine();

        System.out.print("Informe a dimensão X do plano: ");
        int dimensaoX = scanner.nextInt();
        System.out.print("Informe a dimensão Y do plano: ");
        int dimensaoY = scanner.nextInt();

        Plano plano = new Plano(dimensaoX, dimensaoY);

        System.out.print("Informe o número de alunos perdidos: ");
        int numAlunos = scanner.nextInt();
        System.out.print("Informe o número de bugs: ");
        int numBugs = scanner.nextInt();

        if (numAlunos + numBugs >= dimensaoX * dimensaoY / 2) {
            System.out.println("O número de alunos e bugs deve ser menor que a metade do número de células do plano.");
            return;
        }

        Random random = new Random();

        for (int i = 0; i < numAlunos; i++) {
            int posicaoX = random.nextInt(dimensaoX) + 1;
            int posicaoY = random.nextInt(dimensaoY) + 1;
            Aluno aluno = new Aluno(posicaoX, posicaoY);
            plano.adicionarAluno(aluno);
        }

        for (int i = 0; i < numBugs; i++) {
            int posicaoX = random.nextInt(dimensaoX) + 1;
            int posicaoY = random.nextInt(dimensaoY) + 1;
            Bug bug = new Bug(posicaoX, posicaoY);
            plano.adicionarBug(bug);
        }

        for (Robo robo : robos) {
            robo.setPosicaoX(1);
            robo.setPosicaoY(1);
        }

        System.out.println("Iniciando o jogo!");

        int rodada = 1;
        boolean jogoTerminado = false;

        while (!jogoTerminado) {
            System.out.println("Rodada " + rodada);

            for (Robo robo : robos) {
                System.out.println("Jogador: " + nomeJogador);
                System.out.println("Pontuação do robô " + robo.getNome() + ": " + robo.getPontuacao());
                System.out.println("Posição atual do robô " + robo.getNome() + ": (" + robo.getPosicaoX() + ", " + robo.getPosicaoY() + ")");
                System.out.println("Digite a ação para o robô " + robo.getNome() + ":");
                System.out.println("1 - Mover para cima");
                System.out.println("2 - Mover para baixo");
                System.out.println("3 - Mover para a esquerda");
                System.out.println("4 - Mover para a direita");
                System.out.print("Ação: ");

                int acao = scanner.nextInt();

                int numCelulas;
                do {
                    System.out.print("Número de células para mover: ");
                    numCelulas = scanner.nextInt();
                } while (numCelulas <= 0);

                int novaPosicaoX = robo.getPosicaoX();
                int novaPosicaoY = robo.getPosicaoY();

                if (acao == 1) {
                    novaPosicaoY -= numCelulas;
                } else if (acao == 2) {
                    novaPosicaoY += numCelulas;
                } else if (acao == 3) {
                    novaPosicaoX -= numCelulas;
                } else if (acao == 4) {
                    novaPosicaoX += numCelulas;
                }

                if (novaPosicaoX < 1 || novaPosicaoX > dimensaoX || novaPosicaoY < 1 || novaPosicaoY > dimensaoY) {
                    System.out.println("Movimento inválido! Fora dos limites do plano.");
                    continue;
                }

                robo.setPosicaoX(novaPosicaoX);
                robo.setPosicaoY(novaPosicaoY);

                System.out.println("Plano:");
                for (int y = 1; y <= dimensaoY; y++) {
                    for (int x = 1; x <= dimensaoX; x++) {
                        if (x == robo.getPosicaoX() && y == robo.getPosicaoY()) {
                            System.out.print("R ");
                        } else if (verificarAlunoNaPosicao(x, y) != null) {
                            System.out.print("A ");
                        } else if (verificarBugNaPosicao(x, y) != null) {
                            System.out.print("B ");
                        } else {
                            System.out.print(". ");
                        }
                    }
                    System.out.println();
                }
                for (int y = 1; y <= dimensaoY; y++) {
                    for (int x = 1; x <= dimensaoX; x++) {
                        if (x == robo.getPosicaoX() && y == robo.getPosicaoY()) {
                            System.out.print("R ");
                        } else if (x == robo.getPosicaoX() && y == robo.getPosicaoY()) {
                            System.out.print("A ");
                            Aluno alunoResgatado = verificarAlunoNaPosicao(x, y);
                            if (alunoResgatado != null) {
                                alunosResgatados++;
                                robo.adicionarPontuacao(10);
                                System.out.println("Aluno resgatado! +10 pontos");
                                alunos.remove(alunoResgatado);
                            }
                        } else if (x == robo.getPosicaoX() && y == robo.getPosicaoY()) {
                            System.out.print("B ");
                            Bug bugEncontrado = verificarBugNaPosicao(x, y);
                            if (bugEncontrado != null) {
                                bugsEncontrados++;
                                robo.adicionarPontuacao(-15);
                                System.out.println("Bug encontrado! -15 pontos");
                                bugs.remove(bugEncontrado);
                            }
                        } else {
                            System.out.print(". ");
                        }
                    }
                    System.out.println();
                }
            }
            
            

            System.out.println("Alunos resgatados: " + alunosResgatados);
            System.out.println("Bugs encontrados: " + bugsEncontrados);
            

            rodada++;

            if (alunos.isEmpty() || bugs.isEmpty()) {
                jogoTerminado = true;
            }
        }

        System.out.println("Fim do jogo!");
        System.out.println("Relatório:");

        for (Robo robo : robos) {
            System.out.println("Jogador: " + nomeJogador);
            System.out.println("Robô: " + robo.getNome());
            System.out.println("Pontuação: " + robo.getPontuacao());
            System.out.println("Células visitadas: " + robo.getCelulasVisitadas().toString());
            System.out.println("Alunos resgatados: " + alunosResgatados);
            System.out.println("Bugs encontrados: " + bugsEncontrados);
            System.out.println();
        }

        Robo vencedor = obterRoboVencedor();
        System.out.println("Vencedor: " + nomeJogador);
        System.out.println("Robô: " + vencedor.getNome());
        System.out.println("Pontuação: " + vencedor.getPontuacao());
    }

    private Aluno verificarAlunoNaPosicao(int x, int y) {
        for (Aluno aluno : alunos) {
            if (aluno.getPosicaoX() == x && aluno.getPosicaoY() == y) {
                return aluno;
            }
        }
        return null;
    }

    private Bug verificarBugNaPosicao(int x, int y) {
        for (Bug bug : bugs) {
            if (bug.getPosicaoX() == x && bug.getPosicaoY() == y) {
                return bug;
            }
        }
        return null;
    }

    private Robo obterRoboVencedor() {
        Robo vencedor = null;
        int maiorPontuacao = Integer.MIN_VALUE;

        for (Robo robo : robos) {
            if (robo.getPontuacao() > maiorPontuacao) {
                maiorPontuacao = robo.getPontuacao();
                vencedor = robo;
            }
        }

        return vencedor;
    }
    
    public void mostrarStatusRodada() {
        System.out.println("Alunos resgatados: " + alunosResgatados);
        System.out.println("Bugs encontrados: " + bugsEncontrados);
        System.out.println();

        for (Robo robo : robos) {
            System.out.println("Pontuação do robô " + robo.getNome() + ": " + robo.getPontuacao());
            System.out.println("Tabuleiro:");

            for (int y = 1; y <= tamanhoY; y++) {
                for (int x = 1; x <= tamanhoX; x++) {
                    if (x == robo.getPosicaoX() && y == robo.getPosicaoY()) {
                        System.out.print("R ");
                    } else if (verificarAlunoNaPosicao(x, y) != null) {
                        System.out.print("A ");
                    } else if (verificarBugNaPosicao(x, y) != null) {
                        System.out.print("B ");
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }

            System.out.println();
        }
    }

    public void mostrarRelatorio() {
        System.out.println("Relatório:");

        for (Robo robo : robos) {
            System.out.println("Robô: " + robo.getNome());
            System.out.println("Pontuação: " + robo.getPontuacao());
            System.out.println("Células visitadas: " + robo.getCelulasVisitadas());
            System.out.println("Alunos resgatados: " + alunosResgatados);
            System.out.println("Bugs encontrados: " + bugsEncontrados);
            System.out.println();
        }

        Robo vencedor = obterRoboVencedor();
        System.out.println("Vencedor: " + vencedor.getNome());
        System.out.println("Pontuação: " + vencedor.getPontuacao());
    }
    
    
}
