<?php
	header("Access-Control-Allow-Origin: *");
	header("Access-Control-Allow-Headers: *");

	//Dados do Banco de Dados
	include("dados_banco_dados.php");

	//Dados recebidos
	$id = isset($_GET["id"]) ? " WHERE t1.PalavraId = ".$_GET["id"] : "";

	//Variáveis auxiliares
	$palavraLista = array();
	$resposta = [];

	//Configurando conexão
	$conexao   = mysqli_connect($host_name,$user_name,$passoword,$data_base);
	if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
	exit();
	}

	//Gerando query e executando
	$comando   = "SELECT * FROM PALAVRA AS t1".$id;
	$resultado = mysqli_query($conexao,$comando);

	while ($dado = mysqli_fetch_assoc($resultado)){
		$palavraLista["PalavraId"]           = (int) $dado["PalavraId"];
		$palavraLista["Palavra"]             = $dado["Palavra"];
		$palavraLista["PalavraPontos"]       = (int) $dado["PalavraPontos"];	
		$palavraLista["PalavraAcertos"]      = (int) $dado["PalavraAcertos"];	
		$palavraLista["PalavraOpcaoCorreta"] = $dado["PalavraOpcaoCorreta"];	
		$palavraLista["PalavraOpcaoErrada1"] = $dado["PalavraOpcaoErrada1"];	
		$palavraLista["PalavraOpcaoErrada2"] = $dado["PalavraOpcaoErrada2"];	
		$resposta[] = $palavraLista;
	}

	echo json_encode($resposta);
	mysqli_free_result($resultado);
	mysqli_close($conexao);
?>