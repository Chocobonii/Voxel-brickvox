void Bon_chunk_read_config(){
 std::string qry = "SELECT * FROM Server_config ORDER BY Config_Id DESC LIMIT 1";
 
 if(mysql_query(connect,qry.c_str())){
    std::cout<<mysql_errno(connect)<<std::endl;
 }

 res_set = mysql_store_result(connect);         // store messages inside the 
    
 while((row=mysql_fetch_row(res_set))!=NULL){   // while it's fetching messages
  std::string sv_descriptor = row[1];
  
  std::istringstream s_cfg(sv_descriptor);  
  std::string cfg_line = "";  
  
  while(std::getline(s_cfg, cfg_line)){
   char sv_rdtmp[128];     
   char sv_instr[128];
   char sv_prpty[128];
   char sv_value[128];
   strcpy(sv_rdtmp, cfg_line.c_str()); 
   
   sscanf(sv_rdtmp, "%s %s %s", &sv_instr, &sv_prpty, &sv_value);
   // ----- INTERPRETER FOR THE SERVER CONFIGURATION ------
   /*
   ---------------------------------------------------
   - AS THIS IS A SLOW PROCESS MUST HAPPEN ONLY ONCE -
   ---------------------------------------------------
   */
   if(strcmp(sv_prpty, "PLAYER_HGT") == 0){
     py = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "FLOOR_BASE") == 0){
     base_floor = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "FLOOR_SECN") == 0){
     secn_floor = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "WALL_MBASE") == 0){
     base_walls = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "ROOF_MBASE") == 0){
     base_roofs = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "TIME") == 0){
     if(strcmp(sv_value, "DAY") == 0){
      is_night=false;
      glMaterialfv(GL_FRONT, GL_AMBIENT, amb_mt);
      glLightModelfv(GL_LIGHT_MODEL_AMBIENT, amb_md);
     }else{
      has_cloud=false;
      is_night=true;
      glMaterialfv(GL_FRONT, GL_AMBIENT, n_amb_mt);
      glLightModelfv(GL_LIGHT_MODEL_AMBIENT, n_amb_md);
     }
   }
   
   if(strcmp(sv_prpty, "WLEFT_WALL_MODE") == 0){
     if(strcmp(sv_value, "NONE") == 0){
      wleft_wall_gen_mode = 0;
     }else if(strcmp(sv_value, "RANDOM") == 0){
      wleft_wall_gen_mode = 1;
     }else if(strcmp(sv_value, "FULL") == 0){
      wleft_wall_gen_mode = 2;
     }
   }
   
   if(strcmp(sv_prpty, "RIGHT_WALL_MODE") == 0){
     if(strcmp(sv_value, "NONE") == 0){
      right_wall_gen_mode = 0;
     }else if(strcmp(sv_value, "RANDOM") == 0){
      right_wall_gen_mode = 1;
     }else if(strcmp(sv_value, "FULL") == 0){
      right_wall_gen_mode = 2;
     }
   }
   
   if(strcmp(sv_prpty, "FRONT_WALL_MODE") == 0){
     if(strcmp(sv_value, "NONE") == 0){
      front_wall_gen_mode = 0;
     }else if(strcmp(sv_value, "RANDOM") == 0){
      front_wall_gen_mode = 1;
     }else if(strcmp(sv_value, "FULL") == 0){
      front_wall_gen_mode = 2;
     }
   }
   
   if(strcmp(sv_prpty, "WBACK_WALL_MODE") == 0){
     if(strcmp(sv_value, "NONE") == 0){
      wback_wall_gen_mode = 0;
     }else if(strcmp(sv_value, "RANDOM") == 0){
      wback_wall_gen_mode = 1;
     }else if(strcmp(sv_value, "FULL") == 0){
      wback_wall_gen_mode = 2;
     }
   }
   
   if(strcmp(sv_prpty, "WALLS_HGHT_BMIN") == 0){
     min_wall_size = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "WALLS_HGHT_BMAX") == 0){
     max_wall_size = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "WMAKE_ROOF_MODE") == 0){
     if(strcmp(sv_value, "NONE") == 0){
      roof_define_gen_mode = 0;
     }else if(strcmp(sv_value, "RANDOM") == 0){
      roof_define_gen_mode = 1;
     }else if(strcmp(sv_value, "FULL") == 0){
      roof_define_gen_mode = 2;
     }
   }
   
   if(strcmp(sv_prpty, "ROOF_FREQ_BUILD") == 0){
     roof_frequency_of_gen = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "HEIGHT_FLOOR_MD") == 0){
     floor_base_size = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "WORLD_MODE_FLAT") == 0){
     if(strcmp(sv_value, "TRUE") == 0){
      is_flatw = true;
     }else if(strcmp(sv_value, "FALSE") == 0){
      is_flatw = false;
     }
   }
   
   if(strcmp(sv_prpty, "NATURE_GRASS") == 0){
     if(strcmp(sv_value, "TRUE") == 0){
      has_grass = true;
     }else if(strcmp(sv_value, "FALSE") == 0){
      has_grass = false;
     }
   }
   
   if(strcmp(sv_prpty, "WORLD_MODE_ISLE") == 0){
     if(strcmp(sv_value, "TRUE") == 0){
      is_isle = 1;
     }else if(strcmp(sv_value, "FALSE") == 0){
      is_isle = 0;
     }
   }
   
   if(strcmp(sv_prpty, "WVOID_MODE_GENR") == 0){
     if(strcmp(sv_value, "TRUE") == 0){
      wrl_md_void = 1;
     }else if(strcmp(sv_value, "FALSE") == 0){
      wrl_md_void = 0;
     }
   }
   
   if(strcmp(sv_prpty, "NATURE_TREES") == 0){
     if(strcmp(sv_value, "TRUE") == 0){
      has_trees = true;
     }else if(strcmp(sv_value, "FALSE") == 0){
      has_trees = false;
     }
   }
   
   if(strcmp(sv_prpty, "NATURE_BEACH") == 0){
     if(strcmp(sv_value, "TRUE") == 0){
      has_beach = true;
     }else if(strcmp(sv_value, "FALSE") == 0){
      has_beach = false;
     }
   }
   
   if(strcmp(sv_prpty, "SAND_MIN_HGT") == 0){
     sand_min_gen = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "SAND_MAX_HGT") == 0){
     sand_max_gen = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "MOUNTAIN_HGT") == 0){
     world_freq = atoi(sv_value);
   }
   
   if(strcmp(sv_prpty, "WATER_HEIGHT") == 0){
     sea_level = atoi(sv_value);
   }
   // -----------------------------------------------------
  }
 }

 mysql_free_result(res_set);                    // free the result of all messages to prevent leaks
}
