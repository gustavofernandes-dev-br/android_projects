SELECT
    BON_BONUS AS Bonus,
    BON_INICIO_UTILIZACAO,
    BON_VALIDADE AS Validade,
    REG_TIPO_DESCONTO,
    BON_VALOR AS VALOR,
    EXTRATO.VALOR AS VALORDISPONIVEL,
    BON_CODIGO,
    REG_UTILIZAR_FILIAL_ORIGEM,
    REG_TIPO,
    BON_FILIAL_ORIGEM
FROM
    BONUS
    LEFT JOIN REGISTROBONUS (NOLOCK) ON BONUS.BON_CODIGO = REGISTROBONUS.REG_CODIGO
    INNER JOIN (
        SELECT
            BEX_BONUS,
            SUM(BEX_VALOR) AS VALOR
        FROM
            BONUSEXTRATO
        GROUP BY
            BEX_BONUS
    ) EXTRATO ON EXTRATO.BEX_BONUS = BON_BONUS
WHERE
    BON_DOC = '4281211112165029'
UNION
ALL
SELECT
    BON_BONUS as Bonus,
    BON_INICIO_UTILIZACAO,
    BON_VALIDADE as Validade,
    REG_TIPO_DESCONTO,
    BON_VALOR AS VALOR,
    EXTRATO.VALOR AS VALORDISPONIVEL,
    BON_CODIGO,
    REG_UTILIZAR_FILIAL_ORIGEM,
    REG_TIPO,
    BON_FILIAL_ORIGEM
FROM
    BONUS
    LEFT JOIN REGISTROBONUS (NOLOCK) ON BONUS.BON_CODIGO = REGISTROBONUS.REG_CODIGO
    INNER JOIN (
        SELECT
            BEX_BONUS,
            SUM(BEX_VALOR) AS VALOR
        FROM
            BONUSEXTRATO
        GROUP BY
            BEX_BONUS
    ) EXTRATO ON EXTRATO.BEX_BONUS = BON_BONUS
WHERE
    BON_EXCLUIDO = 0
    AND BON_VALOR > 0
    AND (
        BON_INICIO_UTILIZACAO <= '12/11/2021'
        OR BON_INICIO_UTILIZACAO IS NULL
    )
    AND BON_VALIDADE >= '12/11/2021'
    AND BON_CODIGON = '0026170223165148'
    AND (1 = 1)
ORDER BY
    BON_VALIDADE