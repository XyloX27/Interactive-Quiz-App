const questions = [
    {
        question: "What is the capital of France?",
        answers: ["Berlin", "Madrid", "Paris", "Rome"],
        correct: 2 // Index of the correct answer (Paris)
    },
    {
        question: "Which planet is known as the Red Planet?",
        answers: ["Earth", "Mars", "Jupiter", "Saturn"],
        correct: 1 // Mars
    },
    {
        question: "Who wrote 'Hamlet'?",
        answers: ["Shakespeare", "Dickens", "Hemingway", "Austen"],
        correct: 0 // Shakespeare
    }
];

let currentQuestion = 0;
let score = 0;

// Grab elements from the DOM
const questionText = document.getElementById('question-text');
const answersContainer = document.getElementById('answers-container');
const nextButton = document.getElementById('next-btn');
const scoreContainer = document.getElementById('score-container');
const scoreText = document.getElementById('score');

// Function to load the current question and answers
function loadQuestion() {
    const question = questions[currentQuestion];
    questionText.textContent = question.question;
    answersContainer.innerHTML = ''; // Clear previous answers

    // Dynamically create answer buttons (radio buttons or buttons)
    question.answers.forEach((answer, index) => {
        const answerLabel = document.createElement('label');
        const input = document.createElement('input');
        input.type = 'radio';
        input.name = 'answer';
        input.value = index;
        answerLabel.appendChild(input);
        answerLabel.appendChild(document.createTextNode(answer));
        answersContainer.appendChild(answerLabel);
        answersContainer.appendChild(document.createElement('br'));
    });
}

// Function to check the selected answer and update the score
function checkAnswer() {
    const selectedAnswer = document.querySelector('input[name="answer"]:checked');
    if (selectedAnswer) {
        const answerIndex = parseInt(selectedAnswer.value);
        if (answerIndex === questions[currentQuestion].correct) {
            score++;
        }
    }
}

// Function to move to the next question or show the final score
function nextQuestion() {
    checkAnswer();
    currentQuestion++;

    if (currentQuestion < questions.length) {
        loadQuestion();
    } else {
        showScore();
    }
}

// Function to show the final score
function showScore() {
    document.getElementById('quiz-container').style.display = 'none';
    scoreContainer.style.display = 'block';
    scoreText.textContent = score + " / " + questions.length;
}

// Set up event listener for the "Next" button
nextButton.addEventListener('click', nextQuestion);

// Initialize quiz
loadQuestion();
