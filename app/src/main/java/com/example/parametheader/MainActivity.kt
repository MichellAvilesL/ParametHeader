package com.example.parametheader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usuario()
    }
   fun usuario() {
       val txtJson = findViewById<TextView>(R.id.txtJson)
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.bridgecrew.cloud/api/v1/repositories"
       var Cadena: String = ""
       val JsonArrayRq = object: JsonArrayRequest(Method.GET,url,null,
           { response->
                   for (i in 0  until response.length()) {
                       val Jbject: JSONObject = response.getJSONObject(i)
                       val Id: String = Jbject.getString("id")
                       val Repository: String = Jbject.getString("repository")
                       val Source: String = Jbject.getString("source")
                       val IsPublic: String = Jbject.getString("isPublic")
                       val Runs: String = Jbject.getString("runs")
                       val CreationDate: String = Jbject.getString("creationDate")
                       val RastScanDate: String = Jbject.getString("lastScanDate")


                       //Cadena de datos del Json
                       Cadena = Cadena + "Id: $Id \n" +
                               "Repository: $Repository \n" +
                               "Source: $Source \n" +
                               "IsPublic: $IsPublic \n" +
                               "Runs: $Runs \n" +
                               "CreationDate: $CreationDate \n" +
                               "RastScanDate: $RastScanDate \n"+"\n"

                   }
                   txtJson.text = Cadena
            },
            Response.ErrorListener { txtJson.text = "Error!" }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                headers.put("authorization", "57c0305b-ee5e-4a58-81e3-3234ba9bf908")
                return headers
            }
        }
        queue.add(JsonArrayRq)
    }
}
