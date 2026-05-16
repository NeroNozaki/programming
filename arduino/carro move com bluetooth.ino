#include <SoftwareSerial.h>

// A = Sentido horario
// B = Sentido anti horario
// 1 = Roda da esquerda
// 2 = Roda da direita

const int pino1B = 10; // A-1B
const int pino1A = 11; // A-1A
const int pino2A = 4;  // B-1A
const int pino2B = 5;  // B-2A
const int tx = 8;      // Pino TX do módulo Bluetooth conectado ao RX do Arduino
const int rx = 9;      // Pino RX do módulo Bluetooth conectado ao TX do Arduino

// Inicialização do objeto para comunicação Bluetooth
SoftwareSerial bluetooth(rx, tx);


void setup() {
  Serial.begin(9600);
  bluetooth.begin(9600);

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

  if (bluetooth.available() > 0) {
    char input = bluetooth.read();
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
    } else if (input == 'p') {
      parar();
    }
  }
}

void frente() {
  for(int v = 0; v <= 255; v += 5) {    
    analogWrite(pino1A, v);
    analogWrite(pino2A, v);
    delay(30);
  }
  delay(2000);
}

void esquerda() {
  for(int v = 0; v <= 255; v += 5) {    
    analogWrite(pino1B, v);
    analogWrite(pino2A, v);
    delay(30);
  }
  delay(2000);
}

void tras() {
  for(int v = 0; v <= 255; v += 5) {    
    analogWrite(pino1B, v);
    analogWrite(pino2B, v);
    delay(30);
  }
  delay(2000);
}

void direita() {
  for(int v = 0; v <= 255; v += 5) {    
    analogWrite(pino1A, v);
    analogWrite(pino2B, v);
    delay(30);
  }
  delay(2000);
}

void parar() {
  analogWrite(pino1A, 0);
  analogWrite(pino2A, 0);
  analogWrite(pino1B, 0);
  analogWrite(pino2B, 0);
}
