// A = Sentido horario
// B = Sentido anti horario
// 1 = roda esquerda
// 2 = roda direita

const int pino1A = 6; // A-1A
const int pino1B = 7;  // A-1B
const int pino2A = 10;  // B-1A
const int pino2B = 9;  // B-2A

const int echo = 11;   // Lê o pulso
const int trig = 12;   // Envia pulso
long duracao;
float distancia;

void setup() {
  Serial.begin(9600);

  pinMode(trig, OUTPUT);
  pinMode(echo, INPUT);

  pinMode(pino1A, OUTPUT);
  pinMode(pino1B, OUTPUT);
  pinMode(pino2A, OUTPUT);
  pinMode(pino2B, OUTPUT);

  digitalWrite(trig, LOW);
  delayMicroseconds(2);
  digitalWrite(trig, HIGH);
  delayMicroseconds(10);
  digitalWrite(trig, LOW);

  duracao = pulseIn(echo, HIGH);

  distancia = 0.01715 * duracao;

  Serial.println("Iniciando teste.");
}

void loop() {
  digitalWrite(pino1A, LOW);
  digitalWrite(pino1B, LOW);
  digitalWrite(pino2A, LOW);
  digitalWrite(pino2B, LOW);

  digitalWrite(trig, LOW);
  delayMicroseconds(2);
  digitalWrite(trig, HIGH);
  delayMicroseconds(10);
  digitalWrite(trig, LOW);

  duracao = pulseIn(echo, HIGH);

  distancia = 0.01715 * duracao;
  Serial.print("Leitura SENSOR = ");
  Serial.println(distancia);

  if (distancia < 10) {
    parar();
    delay(1000);
    direita();
    delay(500);
  } else {
    frente();
    delay(200);
  }
}

void frente() {  
  analogWrite(pino1B, 255);
  analogWrite(pino2B, 255);
}

void esquerda() {  
  analogWrite(pino1B, 255);
  analogWrite(pino2A, 255);
}

void tras() {
  analogWrite(pino1B, 255);
  analogWrite(pino2B, 255);
}

void direita() {  
  analogWrite(pino1A, 255);
  analogWrite(pino2B, 255);  
}

void parar() {
  analogWrite(pino1A, 0);
  analogWrite(pino2A, 0);
  analogWrite(pino1B, 0);
  analogWrite(pino2B, 0);
}
