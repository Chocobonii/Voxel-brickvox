#include <SFML/Audio.hpp>
#include <iostream>

int main(){
 sf::SoundBuffer buffer;
 if(!buffer.loadFromFile("Data/Music/Sounds/place.wav")){
  return -1;
  std::cout<<"not found"<<std::endl;
 }
 sf::Sound snd;
 snd.setBuffer(buffer);
 std::cout<<"found"<<std::endl;
 snd.setVolume(50.f);
 snd.play();
 while(1){}
 return 0;
}
