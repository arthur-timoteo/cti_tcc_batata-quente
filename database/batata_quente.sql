--
-- Banco de dados: `batata_quente`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `palavra`
--

CREATE TABLE `palavra` (
  `PalavraId` int(11) NOT NULL,
  `Palavra` varchar(50) NOT NULL,
  `PalavraPontos` int(11) NOT NULL,
  `PalavraAcertos` int(11) NOT NULL,
  `PalavraOpcaoCorreta` varchar(50) NOT NULL,
  `PalavraOpcaoErrada1` varchar(50) NOT NULL,
  `PalavraOpcaoErrada2` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `palavra`
--

INSERT INTO `palavra` (`PalavraId`, `Palavra`, `PalavraPontos`, `PalavraAcertos`, `PalavraOpcaoCorreta`, `PalavraOpcaoErrada1`, `PalavraOpcaoErrada2`) VALUES
(1, 'CHOIR', 1, 0, 'Coro', 'Chorar', 'Chão'),
(2, 'TO SLEEP', 1, 0, 'Dormir', 'Sono', 'Cochilar'),
(3, 'KNOWLEDGE', 1, 0, 'Conhecimento', 'Entender', 'Vocação'),
(4, 'WORD', 1, 0, 'Palavra', 'Mundo', 'Madeira'),
(5, 'WORLD', 1, 0, 'Mundo', 'Madeira', 'Palavra'),
(6, 'WORK', 1, 0, 'Trabalho', 'Madeira', 'Construir'),
(7, 'BATHROOM', 1, 0, 'Banheiro', 'Quarto', 'Varanda'),
(8, 'PLEASURE', 1, 0, 'Prazer', 'Licença', 'Satisfação'),
(9, 'ALMOST', 1, 0, 'Quase', 'Aumento', 'Vocação'),
(10, 'DESIRE', 1, 0, 'Desejo', 'Decidir', 'Descontar');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `palavra`
--
ALTER TABLE `palavra`
  ADD PRIMARY KEY (`PalavraId`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `palavra`
--
ALTER TABLE `palavra`
  MODIFY `PalavraId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;