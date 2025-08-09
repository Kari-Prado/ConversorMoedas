# 💱 Conversor de Moedas em Java

Projeto simples de conversor de moedas feito em Java, utilizando:

- API ExchangeRate
- Requisições com `HttpClient`
- JSON com `Gson`
- Interface no terminal com `Scanner`

## 🧪 Moedas disponíveis

- ARS — Peso argentino
- BOB — Boliviano
- BRL — Real brasileiro
- CLP — Peso chileno
- COP — Peso colombiano
- USD — Dólar americano

## 🚀 Como executar

1. Instale o Java 11+
2. Instale o Maven
3. Substitua `MY-API-KEY` no arquivo `CurrencyConverter.java` por sua chave da [ExchangeRate API](https://www.exchangerate-api.com/)
4. Compile e execute:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.example.CurrencyConverter"
