// A = Sentido horario
// B = Sentido anti horario
// 1 = Roda da esquerda
// 2 = Roda da direita

const int pino1B = 10; // A-1B
const int pino1A = 11; // A-1A
const int pino2A = 5;  // B-1A
const int pino2B = 4;  // B-2A
char input;


void setup() {
  Serial.begin(9600);

  pinMode(pino1A, OUTPUT);
  pinMode(pino1B, OUTPUT);
  pinMode(pino2A, OUTPUT);
  pinMode(pino2B, OUTPUT);

  Serial.println("Iniciando teste");
}

void loop() { 
  digitalWrite(pino1A, LOW);
  digitalWrite(pino1B, LOW);
  digitalWrite(pino2A, LOW);
  digitalWrite(pino2B, LOW);

  if (Serial.available() > 0) {
    input = Serial.read();
    Serial.println(input);

    if (input == 'w') {
      frente();
    }
    else if (input == 'a') {
      esquerda();
    }
    else if (input == 's') {
      tras();
    }
    else if (input == 'd') {
      direita();
    }
  }
}

void frente() {
  Serial.println("Indo pra frente.");  
  Serial.println(input);
  for(int v = 0; v <= 255; v += 5) {    
    analogWrite(pino1A, v);
    analogWrite(pino2A, v);
    delay(30);
  }
  delay(2000);
}

void esquerda() {
  Serial.println("Girando pra esquerda.");  
  Serial.println(input);
  for(int v = 0; v <= 255; v += 5) {    
    analogWrite(pino1B, v);
    analogWrite(pino2A, v);
    delay(30);
  }
  delay(2000);
}

void tras() {
  Serial.println("Indo pra tras.");  
  Serial.println(input);
  for(int v = 0; v <= 255; v += 5) {    
    analogWrite(pino1B, v);
    analogWrite(pino2B, v);
    delay(30);
  }
  delay(2000);
}

void direita() {
  Serial.println("Girando pra direita.");  
  Serial.println(input);
  for(int v = 0; v <= 255; v += 5) {    
    analogWrite(pino1A, v);
    analogWrite(pino2B, v);
    delay(30);
  }
  delay(2000);
}
