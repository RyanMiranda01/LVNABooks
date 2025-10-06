package LVNABooks.View;



import LVNABooks.Controller.API.BuscaAPI;
import LVNABooks.Controller.API.converteAPI;
import LVNABooks.Controller.DAO.LivroDAO;
import LVNABooks.Controller.DAO.UsuarioDAO;
import LVNABooks.Model.InformacaoDoVolume;
import LVNABooks.Model.Item;
import LVNABooks.Model.ListaDeLivros;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menus {
    private BuscaAPI buscaAPI = new BuscaAPI();
    private UsuarioDAO usuarioDAO= new UsuarioDAO();
    private int idUsuario;
    private Scanner s = new Scanner(System.in);
    private LivroDAO livroDAO = new LivroDAO();




    public int menuIncial(){

        boolean autorizado = false;
        int escolha = 0;
        do {
            try {
                System.out.println("\nSeja bem-vindo ao LVNABooks, siga as instrucoes abaixo:\n");
                System.out.println("---------------------------------");
                System.out.println("1 - Caso tenha cadastro");
                System.out.println("2 - Caso seja seu primeiro acesso");
                System.out.println("---------------------------------");
                escolha = s.nextInt();
                s.nextLine();

                if (escolha == 1 || escolha == 2) {
                    break;
                }else{
                    System.out.println("Numero inválido!");
                    autorizado = true;
                }

            }catch (InputMismatchException e){
                System.out.println("Só é permitido numeros!");
                s.nextLine();
                autorizado = true;
            }
        } while (autorizado);


        return escolha;
    }




    public void menuDeLogin(){
        boolean autorizado;
        do {
            System.out.println("---------------------------------");
            System.out.println("Digite seu login:");
            String login = s.nextLine();
            System.out.println("Digite sua senha:");
            String senha = s.nextLine();

            autorizado = usuarioDAO.consultarUsuario(login, senha);
            idUsuario = usuarioDAO.consultarUsuarioId(login, senha);
            System.out.println("---------------------------------\n");
        }while(autorizado);

    }

    public void menuCadastro(){
        System.out.println("---------------------------------");
        System.out.println("\nDigite o novo login: ");
        String login = s.nextLine();
        System.out.println("Digite uma nova senha: ");
        String senha = s.nextLine();
        if(usuarioDAO.inserirUsuario(login, senha)){
            System.out.println("Usuario cadastrado com sucesso\n");
        }else{
            System.out.println("Erro ao cadastrar usuario\n");
        }
        System.out.println("---------------------------------");

    }



    public void menuDeEscolha(){
        boolean autorizado = false;
        do {
            try {

                System.out.println("\nOque gostaria de fazer?");
                System.out.println("1 - Ver livros salvos?");
                System.out.println("2 - Pesquisar uma indicação de livro?");
                System.out.println("3 - Sair");
                int resposta = s.nextInt();
                s.nextLine();
                switch (resposta) {
                    case 1:
                        historicoLivro();
                        menuDeEscolha();
                        break;
                    case 2:
                        menuPesquisaLivro();
                        break;
                    case 3:
                        System.out.println("Obrigado por usar o LVNABooks");
                        System.exit(0);
                    default:
                        System.out.println("Valor invalido\n");
                        autorizado = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Só é permitido numeros!");
                s.nextLine();
                autorizado = true;
            }
        }while (autorizado);
    }




    public void menuPesquisaLivro(){
        boolean errado = false;
        do {
            try {
                System.out.println("\nDigite um trecho do titulo do livro que gostaria: ");
                String livro = s.nextLine();

                String json = buscaAPI.buscaAPI(livro);
                ListaDeLivros convertido = converteAPI.converteAPi(json, ListaDeLivros.class);

                if(convertido.getTotalLivros() == 0){
                    System.out.println(convertido.getTotalLivros());
                    System.out.println("Livro não encontrado!");
                    errado = true;

                }else{
                    List<Item> itens = convertido.getLista()
                            .stream()
                            .collect(Collectors.toList());


                    int i = 0;
                    for (Item info : itens) {
                        System.out.println("\nNumero do livro na lista: " + i);
                        System.out.println("------------------------------------");
                        System.out.println(info.getVolumeInfo().toStringDaAPI() + "");
                        System.out.println("------------------------------------");
                        i++;
                    }



                    System.out.println("Gostaria de salvar algum livro?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Nao");
                    int resposta = s.nextInt();


                    switch (resposta) {
                        case 1:
                            System.out.println("Digite o numero do livro na lista que gostaria de salvar: ");
                            int numLivro = s.nextInt();
                            if (livroDAO.salvarLivro(itens.get(numLivro), idUsuario)) {
                                System.out.println("Livro salvo com sucesso!");
                                menuDeEscolha();

                            } else {
                                System.out.println("Livro nao foi salvo");
                            }
                            return;
                        case 2:
                            menuDeEscolha();
                            return;
                        default:
                            System.out.println("Resposta invalida");
                            errado = true;
                            s.nextLine();
                    }
                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }catch (InputMismatchException e){
                System.out.println("Valor invalido! tente novamente");
                errado = true;
                s.nextLine();
            }
        }while(errado);
    }




    public void historicoLivro() {
        boolean errado = false;
                System.out.println("Aqui seu historico:");
                if (livroDAO.listarLivrosSalvos(idUsuario).isEmpty()) {
                    System.out.println("Lista vazia");
                    menuDeEscolha();
                } else
                    for (InformacaoDoVolume inf : livroDAO.listarLivrosSalvos(idUsuario)) {
                        System.out.println(inf.toString());
                    }
        do {
            try {

                System.out.println("\nGostaria de deletar algum livro dos salvos?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                int resposta = s.nextInt();
                s.nextLine();
                switch (resposta) {
                    case 1:
                        System.out.println("Digite o id do livro que gostaria de excluir");
                        int id_livro = s.nextInt();
                        s.nextLine();
                        if (livroDAO.deletarLivro(id_livro)) {
                            System.out.println("Livro excluido com sucesso!");
                        } else {
                            System.out.println("Livro não excluido!");
                        }
                    case 2:
                       return;
                    default:
                        System.out.println("Resposta inválida!");
                        errado = true;
                        s.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Valor invalido! tente novamente");
                s.nextLine();
                errado = true;
            }
        }while(errado);

    }
}


