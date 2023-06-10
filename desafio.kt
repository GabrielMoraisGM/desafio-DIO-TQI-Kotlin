import java.lang.IllegalArgumentException
import java.util.IllegalFormatException

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario (val nome: String)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val xp: Int = 10) {}

data class Formacao(val nome: String){

    var conteudos = mutableSetOf<ConteudoEducacional>()
    val inscritos = mutableListOf<Usuario>()

    override fun toString(): String {
        return "\nFORMAÇÃO: $nome \n" +
                "CONTEUDOS: ${getConteudo()}" +
                "Inscritos: ${getInscritos()} \n"
    }

    fun getConteudo(): String{
        var retornoFormatado: String = "\n"

        for(i in conteudos){
            retornoFormatado += "   Nome: ${i.nome} \n" +
                    "   Duração: ${i.duracao} \n" +
                    "   XP: ${i.xp} \n\n"
        }
        return retornoFormatado
    }

    fun getInscritos(): String{

        var retornoFormatado: String = "\n"

        for(i in inscritos){
            retornoFormatado += "   Usuário: ${i.nome} \n"
        }
        return retornoFormatado
    }

    //adiciona um ou mais conteudos na lista de conteudos, valida se conteudo esta vazio e trata exceção caso seja passado algum valor inesperado
    fun adicionarConteudo(vararg conteudo: ConteudoEducacional){
        if(conteudo.isEmpty()) throw IllegalArgumentException("Você tentou adicionar um conteudo vazio a formação")
        else try {conteudos.addAll(conteudo) } catch (e: IllegalFormatException) { e.stackTrace}

        //exibe o log resultante da execução
        for ( i in conteudo){
            println("[LOG]: adicionado conteudo ${i.nome} á formação $nome")
        }
    }

    //adiciona um ou mais usuarios na formacao, valida se conteudo esta vazio e trata exceção caso seja passado algum valor inesperado
    fun matricular(vararg usuario: Usuario) {

        if(usuario.isEmpty()) throw IllegalArgumentException("Você tentou adicionar um usuário vazio a formação")
        else try {inscritos.addAll(usuario) } catch (e: IllegalFormatException) { e.stackTrace}

        for ( i in usuario){
            println("[LOG]: adicionado ${i.nome} á formação $nome")
        }
        //TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
    }
}
fun main() {

    //instancia os conteudos
    val kotlin = ConteudoEducacional("kotlin", duracao = 120, xp = 20) //sobrescrito valores default
    val java = ConteudoEducacional("Java") //curso java tem os valores default de 60 minutos e 10 de xp

    //instancia um usuario
    var gabriel = Usuario("Gabriel")
    var joao = Usuario("João")

    //instancia uma formação
    val formacaoBackEnd = Formacao("TQI Backend Developer")

    var listaCursos: List<ConteudoEducacional> = listOf(kotlin,java)  //adicione suas instancias de curso aqui para inseri-las no teste
    var listaAlunos: List<Usuario> = listOf(gabriel, joao) //adicione suas instancias de alunos aqui para inseri-las no teste

    //adicione os cursos criados aqui para envialos a função de teste!!
    testeFuncional(formacaoBackEnd, listaCursos, listaAlunos)

}

//essa função testa o tratamento de campos nulos e com dados inesperados da fun
fun testeAdicionarConteudoVazio(formacao: Formacao){
    formacao.adicionarConteudo() //tenta cadastrar um conteudo vazio
}

fun testeMatricularAlunoVazio(formacao: Formacao){
    formacao.matricular() //tenta cadastrar um aluno com dados vazios
}

fun testeFuncional(formacao: Formacao, cursos: List<ConteudoEducacional>, aluno: List<Usuario>){

    for(i in cursos){
        //cadastra os conteúdos na formação
        formacao.adicionarConteudo(i)
    }

    for(i in aluno){
        //cadastra os usuários na formação
        formacao.matricular(i)
    }

    println("[OUTPUT] ${formacao.toString()}")
}
