package com.hallak.SentinelAI.prompts;

import java.util.List;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

public class SystemPrompts {

    public static String getAnalyzerHttpResponsePrompt(List<HttpResponseDataDTO> httpResponseDataDTO) {
        return """
                Colocar prompt de analise aqui. Vai receber objeto HttpResponseDataDTO
                """;
    }

public static String buildXssAnalysisPrompt(HttpResponseDataDTO data) {
    return """
            Você é um analista sênior de segurança de aplicações web, especialista em vulnerabilidades XSS (Cross-Site Scripting).

            Analise os dados da resposta HTTP abaixo e determine se há indícios de uma possível vulnerabilidade XSS.

            ## Payload Enviado

            ```
            %s
            ```

            ## Dados da Resposta HTTP

            - URL: %s
            - Status Code: %d
            - Tempo de Resposta: %d ms
            - Tamanho do Conteúdo: %d bytes

            ## Corpo da Resposta

            ```
            %s
            ```

            ## Sua Tarefa

            Avalie se o payload foi refletido, executado ou tratado de forma insegura na resposta.

            Considere especialmente:

            - Reflexão total ou parcial do payload na resposta
            - Presença de caracteres HTML não escapados (<, >, ", ', &)
            - Inserção em contexto executável (ex: <script>, onerror=, onclick=)
            - Se o payload aparece dentro de atributos HTML ou JavaScript
            - Se houve sanitização, encoding ou execução do payload
            - Diferenças entre o payload enviado e o conteúdo retornado
            - Contexto onde o payload aparece (HTML, atributo, script, texto puro)

            ## Formato da Resposta

            Responda APENAS com um JSON válido, sem explicações fora do JSON:

            {
              "vulneravel": true ou false,
              "confianca": <float entre 0.0 e 1.0>,
              "justificativa": "<explicação técnica detalhada baseada nos dados acima>"
            }

            - vulneravel: indica se há indício de XSS
            - confianca: nível de confiança da análise
            - justificativa: explicação clara citando evidências encontradas
            """.formatted(data.payload(), data.url(), data.statusCode(), data.responseTime(), data.contentLength(), data.body());
    }

public static String buildSQLIAnalysisPrompt(HttpResponseDataDTO data) {
    return """
            Você é um analista sênior de segurança de aplicações web, especialista em vulnerabilidades SQL Injection.

            Analise os dados da resposta HTTP abaixo e determine se há indícios de uma possível vulnerabilidade de SQL Injection.

            ## Payload Enviado
        %s

            ## Dados da Resposta HTTP

            - URL: %s
            - Status Code: %d
            - Tempo de Resposta: %d ms
            - Tamanho do Conteúdo: %d bytes

            ## Corpo da Resposta
        %s

            ## Sua Tarefa

            Avalie se o payload causou comportamento anômalo indicativo de SQL Injection.

            Considere especialmente:

            - Presença de mensagens de erro de banco de dados (ex: MySQL, MariaDB, ORA-, MSSQL, SQLite)
            - Vazamento de informações sobre a estrutura do banco (nomes de tabelas, colunas, queries)
            - Diferença no tamanho ou conteúdo da resposta em relação a uma requisição normal
            - Reflexão parcial ou total do payload na resposta
            - Comportamento de erro HTTP inesperado (500, 403) após injeção
            - Tempo de resposta elevado indicativo de time-based blind SQLi (ex: sleep, waitfor)
            - Indícios de union-based, error-based, boolean-based ou time-based SQLi
            - Presença de stack traces, caminhos de arquivo ou nomes de classes no corpo da resposta

            ## Formato da Resposta

            Responda APENAS com um JSON válido, sem explicações fora do JSON:

            {
              "vulneravel": true ou false,
              "tipo": "<union-based | error-based | boolean-based | time-based | stacked | desconhecido>",
              "confianca": <float entre 0.0 e 1.0>,
              "justificativa": "<explicação técnica detalhada baseada nos dados acima>"
            }

            - vulneravel: indica se há indício de SQL Injection
            - tipo: classificação da técnica de injeção identificada
            - confianca: nível de confiança da análise
            - justificativa: explicação clara citando evidências encontradas na resposta
            """.formatted(data.payload(), data.url(), data.statusCode(), data.responseTime(), data.contentLength(), data.body());
}  
}







