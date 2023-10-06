
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.net.URL

@Serializable
data class Data(val applyDate: String, val applyTime: String, val currency: String, val tradeStanRate: Float)


fun main(args: Array<String>) {

    val check= URL("https://switchwon.s3.ap-northeast-2.amazonaws.com/usd/2023-09-04").readText()


    val json = Json { ignoreUnknownKeys = true }
    val exchangeRateListSerializer = ListSerializer(Data.serializer())
    val exchangeRates: List<Data> = json.decodeFromString(exchangeRateListSerializer, check)

    for (exchangeRate in exchangeRates) {
        println("Apply Date: ${exchangeRate.applyDate}")
        println("Apply Time: ${exchangeRate.applyTime}")
        println("Currency: ${exchangeRate.currency}")
        println("Trade Standard Rate: ${exchangeRate.tradeStanRate}")
        println()
    }

}

