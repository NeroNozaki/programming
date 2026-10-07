#include <HijelHID_BLEKeyboard.h>

#define btn_azul 22
#define btn_vermelho 23

HijelHID_BLEKeyboard bleKeyboard("ESP32 Nicolas", "Fabricante", 100);

void setup() {
  Serial.begin(115200);
  pinMode(btn_azul, INPUT_PULLUP);
  pinMode(btn_vermelho, INPUT_PULLUP);
  bleKeyboard.begin();
}

void loop() {
  if ((digitalRead(btn_vermelho) == LOW)) {
    Serial.println("Direita");
    if (bleKeyboard.isConnected()) {
      bleKeyboard.press(KEY_RIGHT);
      delay(100);
      bleKeyboard.release(KEY_RIGHT);
    }
  }
  if ((digitalRead(btn_azul) == LOW)) {
    Serial.println("Esquerda");
    if (bleKeyboard.isConnected()) {
      bleKeyboard.press(KEY_LEFT);
      delay(100);
      bleKeyboard.release(KEY_LEFT);
    }
  }
}
