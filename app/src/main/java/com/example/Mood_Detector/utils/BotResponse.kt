package com.example.Mood_Detector.utils


import com.example.Mood_Detector.utils.Constants.OPEN_GOOGLE
import com.example.Mood_Detector.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Hello"
                    2 -> "Namaste!"
                    else -> "error" }
            }
            message.contains("what's up") -> {
                when (random) {
                    0 -> "It's a nice day, and i'm looking forward to a good weekend"
                    1 -> "I'm doing great"
                    2 -> "I'm doing fine thanks for asking"
                    else -> "error" }
            }
            //How are you?
            message.contains("how you doing") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks for asking!"
                    1 -> "I'm doing great, thanks for asking. Hope you're doing well too :)"
                    2 -> "Talking to you always makes me feel fabulous! Let me know if there's anything i can do for you"
                    else -> "error"
                }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks for asking!"
                    1 -> "I'm doing great, thanks for asking. Hope you're doing well too :)"
                    2 -> "Talking to you always makes me feel fabulous! Let me know if there's anything i can do for you"
                    else -> "error"
                }
            }

            //Jokes
            message.contains("tell me a joke") -> {
                when (random){
                    0 -> "You know you're texting too much when... ...you say LOL in real life, instead of just laughing. "
                    1 -> "Q: What do you call an iPhone that isn't kidding around? A: Dead Siri-ous"
                    2 -> "Knock! Knock! Who's there? Voodoo. Voodoo who? Voodoo you think you are, asking all these questions?"
                    3 -> "Q. What is the biggest lie in the entire universe? A. I have read and agree to the Terms & Conditions."
                    4 -> "PATIENT: Doctor, I need your help. I'm addicted to checking my Twitter! DOCTOR: I'm so sorry, I don't follow."
                    else -> "error"
                }
            }
            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))
                date.toString()
            }



            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Sorry, I don't understand"
                    else -> "error"
                }
            }
        }
    }
}