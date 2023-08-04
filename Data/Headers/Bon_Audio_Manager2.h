/*
======================================================================
= MAIN THREAD THAT WILL PLAY THE MUSIC AND AUDIO THIS MUST RUN ASYNC =
======================================================================
*/

int rnd_music(){
  std::random_device rd;
  std::mt19937 mt(rd());
  std::uniform_real_distribution<double> dist(1.0, 3000.0);
  float rnd = dist(mt);
  unsigned int flt = rnd;
  return flt;
}

int Bon_AudioM_Init(){
 
 sf::Sound beep1;
 sf::Sound beep2;
 sf::Sound place;
 
 sf::Sound music_calm1;
 
 sf::SoundBuffer beep1_snd;
 sf::SoundBuffer beep2_snd;
 sf::SoundBuffer place_snd;
 sf::SoundBuffer calm1_msc;
 
 if(!beep1_snd.loadFromFile("Data/Music/Sounds/beep1.wav")){
  return -1;
 }else{
  beep1.setBuffer(beep1_snd);
 }
 
 if(!beep2_snd.loadFromFile("Data/Music/Sounds/beep2.wav")){
  return -1;
 }else{
  beep2.setBuffer(beep2_snd);
 }
 
 if(!place_snd.loadFromFile("Data/Music/Sounds/place.wav")){
  return -1;
 }else{
  place.setBuffer(place_snd);
 }
 
 if(!calm1_msc.loadFromFile("Data/Music/Music/calm1.wav")){
  return -1;
 }else{
  music_calm1.setBuffer(calm1_msc);
 }
 
 while(isRunning2==false){/*std::cout<<"waiting for program to start...."<<std::endl;*/usleep(150*1000);}

 while(1){
  
  if(rnd_music()==50||play_music==true){
    music_calm1.play();
    play_music=false;
  }
  
  if(snd_beep1 == true){
    beep1.play();
  }
  if(snd_beep2 == true){
    beep2.play();
  }
  if(snd_place == true){
    place.play();
  }
  snd_beep1 = false;
  snd_beep2 = false;
  snd_place = false;
  usleep(330*1000);
 }
}

