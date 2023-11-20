// object Main extends App {
//   println("Hello, World!")
// }
import scala.io.StdIn._
object Main {
  def main(args: Array[String]): Unit = {
    //Choix de l'action
    val possibilities = Array("Deposit","Withdrawal","Account_consultation","Finish")

    //somme initiale
    var owner_equity : Double = 1200.0

    var choice = "0"
    val CODEPIN = "INTRO1234"
    var PINCODE = "no solution"
    var attempt = 3
    
    
    //possibilité de faire plusieurs actions
    while(choice!="Finish"){
      println("1: Deposit")
      println("2: Withdrawal")
      println("3: Account consultation")
      println("4: Finish")

      var choice = possibilities (readLine().toInt-1)
      println(choice)
      
      //entrer le code pin
      if (choice != "Finish"){
        while((attempt != 0)&&(PINCODE != CODEPIN)){
          PINCODE = readLine("enter your pin code: ").toString
          if(PINCODE != CODEPIN){
            attempt -= 1
            println("Invalid PIN code, you have: "+ attempt +" attempts remaining.")
          }
        }
        if ((PINCODE != CODEPIN)&&(attempt==0)){
          println("For your protection, banking operations will be suspended, please retrieve your card.")
          
        }
      }
      
      //Dépôt d'argent
      if((choice == "Deposit")&&(PINCODE == CODEPIN)){
        var cash_deposit = 0.0
        var montant2 = 1.0
        var money = Array("CHF","EUR")
        var moneychoosen = money(readLine("Indicate the currency of the deposit: 1) CHF; 2) EUR :").toInt -1)

        //Le dépôt doit être suppérieur à 10, et divisible par 10, sinon, redemander.
        do{
          cash_deposit = readLine("Indicate the amount of the deposit :").toDouble
          var montant = cash_deposit / 10.0
          montant2 = montant - (montant.toInt)
          if((cash_deposit <= 10)||(montant2 != 0))println("The amount must be a multiple of 10")
        }         
        while((cash_deposit <= 10)||(montant2 != 0))
        
        //convertir la monnaie en CHF
        if (moneychoosen == "EUR")cash_deposit *= 0.95

        //total de fric
        owner_equity += cash_deposit
        println("Your deposit has been processed, the new amount available on your account is :"+owner_equity+" CHF")


      //Retrait d'argent 
      }else if ((choice == "Withdrawal")&&(PINCODE == CODEPIN)){
        var withdrawal = 0.0
        var authorized = owner_equity * 0.1
        var money = 0
        var divisible2 : Double = 1

        var number = 0
        var rest = withdrawal

        var newrest = rest
        do{
          money = readLine("Indicate the currency: 1) CHF; 2) EUR").toInt

          //faut il prendre en compte le fait que cela puisse être des EUR ?
          if((money == 1)||(money == 2)){
            
            do{
              withdrawal = readLine("Indicate the amount of the withdrawal").toDouble


              //dépôt maximum autorisé en fonction de l'argent présent sur le compte
              
              if(withdrawal > authorized)println("Your authorized withdrawal limit is :"+ authorized)

              //divisible par 10
              var divisible = withdrawal / 10
              divisible2 = divisible - (divisible.toInt)
              if(divisible2 != 0)println("The amount must be a multiple of 10")
            }
            while ((divisible2 != 0)||(withdrawal > authorized))

          }
          
        }
        while((money != 1)&&(money != 2))

        if(money == 1){
          
          var denominationavailable = Array(500,200,100,50,20,10) 
          
          //en quelle coupures on veut retirer
          var denomination = 0
          if(withdrawal >= 200){
            while((denomination != 1)&&(denomination != 2)){
              denomination = readLine("In 1) large denominations, 2) small denominations").toInt

              //la décomposition
              

              var banknote500 = "0"
              var banknote200 = "0"
              var banknote100 = "0"
              var banknote50 = "0"
              var banknote20 = "0"
              var banknote10 = 0

              var number500 = 0
              var number200 = 0
              var number100 = 0
              var number50 = 0
              var number20 = 0
              var number10 = 0
              
              if(denomination == 1){

                //L'idée est de faire une boucle qui crée de nouvelles variable pour chaque nombre de billet proposé, afin de déterminer le choix de chaque billet
                for (i<- 0 to 5){
                  number = 0
                  while(rest >= 0){
                    rest -= denominationavailable(i)
                    if (rest >= 0)number += 1
                  }
                  if(rest < 0)rest += denominationavailable(i)

  //pour les billets de 500 CHF
                  if((i == 0)&&(number > 0)){
                    number500 = number
                    do{
                      println("There is "+ withdrawal +"CHF left to distribute")
                      println("You can get a maximum of "+ number +"bill(s) of "+ denominationavailable(i) +"CHF")
                      banknote500 = readLine("Type 'o' for OK or any other value less than the proposed one").toString
                    }
                    while((banknote500.toInt >= number)&&(banknote500 != "o"))
                   //il faut faire en sorte que le reste soit dépendant du nombre choisi

                    if ((banknote500 != "0")&&(banknote500 != "o")){
                      rest = withdrawal - banknote500.toInt * denominationavailable(i)
                      newrest = rest
                    }else if (banknote500 == "o")newrest=rest
                    else rest = newrest
                  }
                                    
  //pour les billets de 200 CHF
                  if((i == 1)&&(number > 0)){
                    number200 = number
                    do{
                      println("There is "+ newrest +"CHF left to distribute")
                      println("You can get a maximum of "+ number +"bill(s) of "+ denominationavailable(i) +"CHF")
                      banknote200 = readLine("Type 'o' for OK or any other value less than the proposed one").toString
                    }
                    while((banknote200.toInt >= number)&&(banknote200 != "o"))
                   //il faut faire en sorte que le reste soit dépendant du nombre choisi

                    if (banknote200 != "0"){
                      rest = newrest - banknote200.toInt * denominationavailable(i)
                      newrest = rest
                    }else if (banknote200 == "o")newrest=rest
                    else rest = newrest
                  }

  //pour les billets de 100 CHF
                  if((i == 2)&&(number > 0)){
                    number100 = number
                    do{
                      println("There is "+ newrest +"CHF left to distribute")
                      println("You can get a maximum of "+ number +"bill(s) of "+ denominationavailable(i) +"CHF")
                      banknote100 = readLine("Type 'o' for OK or any other value less than the proposed one").toString
                    }
                    while((banknote100.toInt >= number)&&(banknote100 != "o"))
                   //il faut faire en sorte que le reste soit dépendant du nombre choisi

                    if (banknote100 != "0"){
                      rest = newrest - banknote100.toInt * denominationavailable(i)
                      newrest = rest
                    }else if (banknote100 == "o")newrest=rest
                    else rest = newrest
                  }
                  
  //pour les billets de 50 CHF
                  if((i == 3)&&(number > 0)){
                    number50 = number
                    do{
                      println("There is "+ newrest +"CHF left to distribute")
                      println("You can get a maximum of "+ number +"bill(s) of "+ denominationavailable(i) +"CHF")
                      banknote50 = readLine("Type 'o' for OK or any other value less than the proposed one").toString
                    }
                    while((banknote50.toInt >= number)&&(banknote50 != "o"))
                   //il faut faire en sorte que le reste soit dépendant du nombre choisi

                    if (banknote50 != "0"){
                      rest = newrest - banknote50.toInt * denominationavailable(i)
                      newrest = rest
                    }else if (banknote50 == "o")newrest=rest
                    else rest = newrest
                  }

  //pour les billets de 20 CHF
                  if((i == 4)&&(number > 0)){
                    number20 = number
                    do{
                      println("There is "+ newrest +"CHF left to distribute")
                      println("You can get a maximum of "+ number +"bill(s) of "+ denominationavailable(i) +"CHF")
                      banknote20 = readLine("Type 'o' for OK or any other value less than the proposed one").toString
                    }
                    while((banknote20.toInt >= number)&&(banknote20 != "o"))
                   //il faut faire en sorte que le reste soit dépendant du nombre choisi

                    if (banknote20 != "0"){
                      rest = newrest - banknote20.toInt * denominationavailable(i)
                      newrest = rest
                    }else if (banknote20 == "o")newrest=rest
                    else rest = newrest
                  }

//pour les billets de 10 CHF
                  if((i == 5)&&(number > 0)){
                    number10 = number
                    println("There is "+ newrest +"CHF left to distribute")
                    println("You get "+ number +"bill(s) of "+ denominationavailable(i) +"CHF")
                    banknote10 = number

                  }
                }
                if ((banknote500 == 0)&&(banknote200 == 0)&&(banknote100 == 0)&&(banknote50 == 0)&&(banknote20 == 0)){
                  for(i<- 0 to 5){
                    var number = 0
                    while(rest >= 0){
                      rest -= denominationavailable(i)
                      if (rest >= 0)number += 1
                    }
                    if(rest < 0)rest += denominationavailable(i)
                    if (number > 0)println("You take "+ number +" banknote of "+ denominationavailable(i) +"CHF")
                }
                }else{
                    println("Please withdraw the requested amount :")
                    if(banknote500.toInt != 0)println(banknote500 +"bill(s) of 500 CHF")
                    if(banknote200.toInt != 0)println(banknote200 +"bill(s) of 200 CHF")
                    if(banknote100.toInt != 0)println(banknote100 +"bill(s) of 100 CHF")
                    if(banknote50.toInt != 0)println(banknote50 +"bill(s) of 50 CHF")
                    if(banknote20.toInt != 0)println(banknote20 +"bill(s) of 20 CHF")
                    if(banknote10.toInt != 0)println(banknote10 +"bill(s) of 10 CHF")



                
              }
              } 
            }
          }else if ((withdrawal < 200)||(denomination == 2)){
            //la décomposition
            
            for(i<- 2 to 5){
              var number = 0
              while(rest >= 0){
                rest -= denominationavailable(i)
                if (rest >= 0)number += 1
              }
              if(rest < 0)rest += denominationavailable(i)
              if(number > 0)println("You take "+ number +" banknote of "+ denominationavailable(i) +"CHF")
            }
          }
        }

        //si la monnaie choisie se trouve être l'EUR

        else{


          var bank100 = "0"
          var bank50 = "0"
          var bank20 = "0"
          var bank10 = 0

          var nber100 = 0
          var nber50 = 0
          var nber20 = 0
          var nber10 = 0


          
          var deno_available = Array(100,50,20,10)
          var nber = 0

          for (i<- 0 to 3){
            nber = 0
            while(rest >= 0){
              rest -= deno_available(i)
              if (rest >= 0)nber += 1
            }
            if(rest < 0)rest += deno_available(i)

          //pour les billets de 100 EUR
            if((i == 0)&&(nber > 0)){
              nber100 = nber
              do{
                println("There is "+ withdrawal +"EUR left to distribute")
                println("You can get a maximum of "+ nber +"bill(s) of "+ deno_available(i) +"EUR")
                bank100 = readLine("Type 'o' for OK or any other value less than the proposed one").toString
              }
              while((bank100.toInt >= nber)&&(bank100 != "o"))
             //il faut faire en sorte que le reste soit dépendant du nombre choisi

              if ((bank100.toString != "0")&&(bank100.toString != "o")){
                rest = withdrawal - bank100.toInt * deno_available(i)
                newrest = rest
              }else if (bank100 == "o")newrest=rest
              else rest = newrest
            }

          //pour les billets de 50 EUR
            if((i == 1)&&(nber > 0)){
              nber50 = nber
              do{
                println("There is "+ newrest +"EUR left to distribute")
                println("You can get a maximum of "+ nber +"bill(s) of "+ deno_available(i) +"EUR")
                bank50 = readLine("Type 'o' for OK or any other value less than the proposed one").toString
              }
              while((bank50.toInt >= nber)&&(bank50 != "o"))
             //il faut faire en sorte que le reste soit dépendant du nombre choisi

              if (bank50 != "0"){
                rest = newrest - bank50.toInt * deno_available(i)
                newrest = rest
              }else if (bank50 == "o")newrest=rest
              else rest = newrest
            }

          //pour les billets de 20 EUR
            if((i == 2)&&(nber > 0)){
              nber20 = nber
              do{
                println("There is "+ newrest +"EUR left to distribute")
                println("You can get a maximum of "+ nber +"bill(s) of "+ deno_available(i) +"EUR")
                bank20 = readLine("Type 'o' for OK or any other value less than the proposed one").toString
              }
              while((bank20.toInt >= nber)&&(bank20 != "o"))
             //il faut faire en sorte que le reste soit dépendant du nombre choisi

              if (bank20 != "0"){
                rest = newrest - bank20.toInt * deno_available(i)
                newrest = rest
              }else if (bank20 == "o")newrest=rest
              else rest = newrest
            }

          //pour les billets de 10 EUR
            if((i == 3)&&(nber > 0)){
              nber10 = nber
              println("There is "+ newrest +"EUR left to distribute")
              println("You get "+ nber +"bill(s) of "+ deno_available(i) +"EUR")
              bank10 = nber

            }
          }
          if ((bank100 == 0)&&(bank50 == 0)&&(bank20 == 0)){
            for(i<- 0 to 3){
              var nber = 0
              while(rest >= 0){
                rest -= deno_available(i)
                if (rest >= 0)nber += 1
              }
              if(rest < 0)rest += deno_available(i)
              if (nber > 0)println("You take "+ nber +" banknote of "+ deno_available(i) +"EUR")
          }
          }else{
              println("Please withdraw the requested amount :")
             
              if(bank100.toInt != 0)println(bank100 +"bill(s) of 100 EUR")
              if(bank50.toInt != 0)println(bank50 +"bill(s) of 50 EUR")
              if(bank20.toInt != 0)println(bank20 +"bill(s) of 20 EUR")
              if(bank10.toInt != 0)println(bank10 +"bill(s) of 10 EUR")


        }
        }
        if (money == 2)withdrawal *= 0.95
        

        owner_equity -= withdrawal
        printf("Your withdrawal has been processed, the new amount available on your account is : %.2f\n",owner_equity)
        print("CHF")

      //Consultation du compte
      }else if ((choice == "Account_consultation")&&(PINCODE == CODEPIN)){
        println("You have :" + owner_equity + " CHF in your account")
      }else if (choice == "Finish")println("End of operations, don't forget to collect your card")
    }
  }
}
