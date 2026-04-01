package com.hallak.SentinelAI.prompts;

import java.util.List;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

public class SystemPrompts {

    public static String getAnalyzerHttpResponsePrompt(List<HttpResponseDataDTO> httpResponseDataDTO) {
        return """
                Colocar prompt de analise aqui. Vai receber objeto HttpResponseDataDTO
                """;
    }

public static String buildXssAnalysisPrompt(HttpResponseDataDTO data, String payload) {
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
            """.formatted(payload, data.url(), data.statusCode(), data.responseTime(), data.contentLength(), data.body());
    }    
}







