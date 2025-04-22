import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class Consultorio {

    private int idConsultorio;
    private String nomeConsultorio;
    private String endereco;
    private String telefone;

    private static ArrayList<Consultorio> listaConsultorios = new ArrayList<Consultorio>();

    //getters e setters
    public Consultorio(){
    }

    public Consultorio(String nomeConsultorio, String endereco, String telefone){
        this.idConsultorio = listaConsultorios.size();
        this.nomeConsultorio = nomeConsultorio;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public static void cadastrarConsultorio(){
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o nome do consultorio: ");
        String nomeConsultorio = entrada.nextLine();
        System.out.print("Digite o telefone do consultorio: ");
        String telefone = entrada.nextLine();
        System.out.print("Digite o endereço do consultorio: ");
        String endereco = entrada.nextLine();

        Consultorio novoConsultorio = new Consultorio(nomeConsultorio, telefone, endereco);
        String sql = "INSERT INTO consultorio (nomeConsultorio, telefone, endereco) " +
                "VALUES ('" + nomeConsultorio + "', '" + telefone + "', '" + endereco + "');";

        boolean salvo = ConexaoBD.salvar(sql);

        if(salvo){
            System.out.println("\nConsultório cadastrado com sucesso!\n");
        }else{
            System.out.println("\nProblemas ao adicionar o consultório!\n");
        }
    }

    public static void mostrarConsultorios() throws SQLException {

        String sql = "SELECT * FROM consultorio;";
        ResultSet resultado = ConexaoBD.buscar(sql);
        listaConsultorios = new ArrayList<Consultorio>();
        while(resultado.next()){
            Consultorio consultorioEncontrado = new Consultorio();
            consultorioEncontrado.idConsultorio = resultado.getInt("idConsultorio");
            consultorioEncontrado.nomeConsultorio = resultado.getString("nomeConsultorio");
            consultorioEncontrado.telefone = resultado.getString("telefone");
            consultorioEncontrado.endereco = resultado.getString("endereco");
            listaConsultorios.add(consultorioEncontrado);
        }

        System.out.print("\n\nCONSULTÓRIOS CADASTRADOS: ");
        System.out.println(listaConsultorios.size());
        System.out.println("ID \t Nome \t\t Telefone \t\t Endereço");

        for(int i=0; i < listaConsultorios.size() ; i++){
            System.out.println(listaConsultorios.get(i).idConsultorio +
                    "\t" + listaConsultorios.get(i).nomeConsultorio +
                    "\t\t" + listaConsultorios.get(i).telefone +
                    "\t\t" + listaConsultorios.get(i).endereco);
        }
        System.out.println("\n\n");
    }

    public static void deletarConsultorio(){
        Scanner entrada = new Scanner(System.in);
        int consultorioExcluido;
        System.out.print("Informe o ID do consultório que deseja deletar: ");
        consultorioExcluido = entrada.nextInt();
        String sql = "DELETE FROM consultorio WHERE idConsultorio = " + consultorioExcluido;
        boolean dadoExcluido = ConexaoBD.deletar(sql);
        if(dadoExcluido) {
            System.out.println("Consultório removido com sucesso!\n");
        }else{
            System.out.println("Problemas ao excluir o consultório!\n");
        }
    }

    public static void alterarConsultorio() throws SQLException {
        Scanner entrada = new Scanner(System.in);
        if (listaConsultorios.isEmpty()) {
            System.out.println("Nenhum consultório cadastrado.");
            return;
        }

        System.out.print("Escolha o consultório que deseja alterar:");
        int opcao = entrada.nextInt();
        Consultorio consultorio = listaConsultorios.get(opcao);

        ArrayList<String> alteracoes = new ArrayList<String>();

        System.out.println("Alterando dados do consultório: " + consultorio.nomeConsultorio);
        System.out.print("Novo nome (ou enter para manter): ");
        entrada.nextLine();
        String novoNome = entrada.nextLine();
        if (!novoNome.isEmpty()) {
            alteracoes.add("UPDATE consultorio SET nomeConsultorio = '" + novoNome +
                    "' WHERE idConsultorio = " + opcao + ";");
            consultorio.nomeConsultorio = novoNome;
        }

        System.out.print("Novo telefone (ou enter para manter): ");
        String novoTelefone = entrada.nextLine();
        if (!novoTelefone.isEmpty()) {
            alteracoes.add("UPDATE consultorio SET telefone = '" + novoTelefone +
                    "' WHERE idConsultorio = " + opcao + ";");
            consultorio.telefone = novoTelefone;
        }

        System.out.print("Novo endereço (ou enter para manter): ");
        String novoEndereco = entrada.nextLine();
        if (!novoEndereco.isEmpty()) {
            alteracoes.add("UPDATE consultorio SET endereco = '" + novoEndereco +
                    "' WHERE idConsultorio = " + opcao + ";");
            consultorio.endereco = novoEndereco;
        }

        if(alteracoes.size()>0){
            for (int i=0; i<alteracoes.size(); i++){
                boolean dadosAlterados = ConexaoBD.atualizar(alteracoes.get(i));
            }
        }
        System.out.println("Dados do consultório atualizados com sucesso!\n");
    }
}