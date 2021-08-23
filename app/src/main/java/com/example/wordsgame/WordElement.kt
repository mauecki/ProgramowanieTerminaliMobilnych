package com.example.wordsgame

import java.util.*

class WordElement(val wordType: String="Numbers") {


    private val Numbers: Array<String> = arrayOf("one",
        "two", "three","four", "five", "six", "seven", "eight", "nine",
        "ten")
    private val Animals: Array<String> = arrayOf(
        "Bison", "Dolphin", "Eagle", "Pony", "Monkey", "Cow", "Deer",
        "Duck", "Rabbit", "Spider", "Wolf", "Turkey", "lion", "Pig",
        "Snake", "Shark", "Bird", "Bear", "Fish", "Chicken", "Horse", "Cat",
        "Dog","Bison", "Dolphin", "Eagle", "Pony", "Monkey", "Cow", "Deer",
        "Duck", "Rabbit", "Spider", "Wolf", "Turkey", "lion", "Pig", "Snake",
        "Shark", "Bird", "Bear", "Fish", "Chicken", "Horse", "Cat", "Dog")
    private val Cities: Array<String> = arrayOf(
        "Paris", "New York", "London", "Bangkok", "Hong Kong", "Dubai", "Singapore",
        "Rome", "Chicago", "Istanbul", "Delhi", "Tokyo", "Antalya", "Berlin", "Madrid",
        "Barcelona", "Milan", "Lyon", "Amsterdam", "Dortmund", "Manchester", "Lisbon",
        "Moscow", "Prague", "Budapest", "Warsaw", "Posen", "Cracow", "Washington", "Wien"
    )
    private val Professions: Array<String> = arrayOf(
        "Architect", "Builder", "Cleaner", "Cook", "Dentist", "Economist", "Engineer",
        "Electrician", "Farmer", "Firefighter", "Hairdresser", "Instructor", "Journalist",
        "Lawyer", "Lecturer", "Librarian", "Driver", "Pilot", "Plumber", "Musician", "Postman",
        "Nurse", "Scientist", "Secretary", "Teacher", "Mechanic", "Doctor", "Dressmaker",
        "Chef", "Miner", "Director", "Chemist", "Priest", "Policeman", "Magician", "Dancer",
        "Judge", "Swimmer", "Footballer", "Photographer", "Waiter")
    private val Sports: Array<String> = arrayOf(
        "Aerobics", "Athletics", "Badminton", "Baseball", "Basketball", "Bowling", "Boxing",
        "Chess", "Climbing", "Cycling", "Dancing", "Diving", "Fencing", "Football", "Golf",
        "Gymnastics", "Hockey", "Jogging", "Judo", "Karate", "Kung Fu", "Marathon", "Rowing",
        "Rugby", "Running", "Sailing", "Skating", "Skiing", "Snowboarding", "Sprint", "Swimming",
        "Tennis", "Volleyball", "Wrestling", "Ping Pong")


    fun dictionaryChoice(wordType: String): Array<String> {
        return when(wordType){
            "Numbers" -> Numbers
            "Animals" -> Animals
            "Cities" -> Cities
            "Careers" -> Professions
            "Sports" -> Sports
            else -> Numbers
        }
    }

    fun wordGen(): String {
        val finalDictionary = dictionaryChoice(wordType)
        val wordLength: Int = dictionaryChoice(wordType).size - 1
        val wordNum: Int = (0..wordLength).random()
        val wordNum2: Int = Random().nextInt(wordLength) +1
        val finalPosition = ((wordNum+wordNum2)%(wordLength+1))
        return finalDictionary[finalPosition]
    }

    fun wordMix(word: String): String {
        val charTemp = word.toCharArray()
        charTemp.shuffle()
        return charTemp.concatToString()
    }
}