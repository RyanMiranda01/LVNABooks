package LVNABooks;



import LVNABooks.View.Menus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LvnaBooksApplication implements CommandLineRunner {
        Menus menus = new Menus();


	public static void main(String[] args) {
		SpringApplication.run(LvnaBooksApplication.class, args);
	}



    @Override
    public void run(String... args){
    switch (menus.menuIncial()) {
        case 1:

            menus.menuDeLogin();
            menus.menuDeEscolha();
            break;
        case 2:
            menus.menuCadastro();
            menus.menuDeLogin();
            menus.menuDeEscolha();
            break;

    }






    }
}
