package robo;
import java.util.*;

public class Jogo {
	public static void main(String[] args) {
        Plano plano = new Plano(0, 0);

        Robo andador = new Robo("Andador");
        Robo peao = new Robo("Peão");
        Robo torre = new Robo("Torre");
        Robo bispo = new Robo("Bispo");
        Robo cavalo = new Robo("Cavalo");
        Robo rei = new Robo("Rei");
        Robo rainha = new Robo("Rainha");

        plano.adicionarRobo(andador);
        plano.adicionarRobo(peao);
        plano.adicionarRobo(torre);
        plano.adicionarRobo(bispo);
        plano.adicionarRobo(cavalo);
        plano.adicionarRobo(rei);
        plano.adicionarRobo(rainha);

        plano.iniciarJogo();
	}
}
