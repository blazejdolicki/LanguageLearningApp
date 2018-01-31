The programme is a simple language learning app. First it retrieves a list of words in two different languages from Slownik.txt. 
Then a user can either do an exercise, or translate a word (which is in the list) from one language to another. 

If the user decides to do an exercise, he has to translate random words from one to another language. Before the exercise start he can choose from and to which language he is translating and also the amount of words he wants to translate. 

If he is not able to translate some word, he can ask for a clue - then he is shown the desired word with several blank spaces instead of letters. If, after using the clue, user will write the correct translation, he will get 0.5 for it. However, if he answers incorrectly, he won't get any minus points. 

After finishing the exercise the result window appears, where user can see how many points he reached, his percentage score, number of clues he asked for and number of clues that actually helped him to write the correct answer.

Every word that was translated by the user correctly, will have significantly smaller probability of occurence in the next exercises. Nevertheless, after each exercise session, the probability of presence of this word in the next exercise will slighlty increment (since we forget words over time, it is desired to repeat them after some period of time). Probabilities of all words are stored in a text file which is created after first exercise done by the user.
