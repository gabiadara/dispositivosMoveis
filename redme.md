1. O ciclo de vida da activity garante que o android libere memoria quando necessário e não mantenha telas inativas ocupando recurso.
   onSaveInstanceState() salva dados temporários
   onRestoreInstanceState recupera esses dados quando a activity é recriada
   State Restoration é restaurar o estado da tela para que o usuário continue onde de parou (sem perder informação)

2. AndroidManifest: descreve o app
   res: recursos (imagens, estilos, strings...)
   R.java: classe gerada automaticamente que da acesso aos recursos do res