package robo;
import java.util.*;

public class Robo {
	private String nome;
    private int posicaoX;
    private int posicaoY;
    private int pontuacao;
    private List<String> celulasVisitadas;

    public Robo(String nome) {
        this.nome = nome;
        this.posicaoX = 1;
        this.posicaoY = 1;
        this.pontuacao = 0;
        this.celulasVisitadas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void adicionarPontuacao(int pontos) {
        pontuacao += pontos;
    }

    public List<String> getCelulasVisitadas() {
        return celulasVisitadas;
    }
}
