package at.technikum.if20b231.newslist.handler

import android.util.Log
import at.technikum.if20b231.newslist.modle.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.text.ParseException

class NewsDownloader {
    companion object {
        val LOG_TAG: String = NewsDownloader::class.java.canonicalName ?: "RssParser"
    }

    suspend fun load(urlString: String): List<Page>? {
        return withContext(Dispatchers.IO) {
            loadInt(urlString)
        }
    }

    private fun loadInt(urlString: String) : List<Page>? {
        return try {
            Log.d(LOG_TAG, "Start downloading $urlString ...")
            val url = URL(urlString)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            try {
                urlConnection.connectTimeout = 5000
                if (urlConnection.responseCode != HttpURLConnection.HTTP_OK) {
                    Log.w(
                        LOG_TAG,
                        "Error opening RSS feed, Error code: ${urlConnection.responseCode}"
                    )
                    return null
                }
                Log.d(LOG_TAG, "Start parsing ...")
                val parser = XMLParser()
                val result = parser.parse(urlConnection.inputStream)
                Log.d(LOG_TAG, "Parsing finished.")
                result
            } finally {
                urlConnection.disconnect()
            }
        } catch (ex: MalformedURLException) {
            Log.w(
                LOG_TAG,
                String.format("Error opening RSS feed, Feed %1\$s url invalid.", urlString),
                ex
            )
            null
        } catch (ex: IOException) {
            Log.w(LOG_TAG, "Error reading RSS feed.", ex)
            null
        } catch (ex: ParseException) {
            Log.w(LOG_TAG, "Error parsing RSS feed.", ex)
            null
        } catch (ex: XmlPullParserException) {
            Log.w(LOG_TAG, "Error parsing RSS feed.", ex)
            null
        }
    }
}