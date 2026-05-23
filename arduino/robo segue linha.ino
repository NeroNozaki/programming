// A = Sentido horario
// B = Sentido anti horario
// 1 = esquerda
// 2 = direita

const int pino1B = 2;  // A-1B
const int pino1A = 3;  // A-1A
const int pino2A = 4;  // B-1A
const int pino2B = 5;  // B-2A
const int sens1 = 9;   // sensor esquerda (0)
const int sens2 = 10;  // sensor direita (0)

void setup() {
  Serial.begin(9600);

  pinMode(pino1A, OUTPUT);
  pinMode(pino1B, OUTPUT);
  pinMode(pino2A, OUTPUT);
  pinMode(pino2B, OUTPUT);

  pinMode(sens1, INPUT);
  pinMode(sens2, INPUT);

  Serial.println("Iniciando teste.");
}

void loop() {
  digitalWrite(pino1A, LOW);
  digitalWrite(pino1B, LOW);
  digitalWrite(pino2A, LOW);
  digitalWrite(pino2B, LOW);

  if (digitalRead(sens1) == 1 and digitalRead(sens2) == 1) {
    frente();
    Serial.println("frente");
  } else if (digitalRead(sens1) == 0) {
    esquerda();
    Serial.println("esquerda");
  } else {
    direita();
    Serial.println("direita");
}

}

void frente() {  
  analogWrite(pino1B, 255);
  analogWrite(pino2B, 255);
}

void esquerda() {  
  analogWrite(pino1A, 255);
  analogWrite(pino2B, 255);
}

void tras() {
  analogWrite(pino1B, 255);
  analogWrite(pino2B, 255);
}

void direita() {  
  analogWrite(pino1B, 255);
  analogWrite(pino2A, 255);
}

void parar() {
  analogWrite(pino1A, 0);
  analogWrite(pino2A, 0);
  analogWrite(pino1B, 0);
  analogWrite(pino2B, 0);
}
