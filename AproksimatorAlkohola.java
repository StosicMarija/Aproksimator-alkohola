package osmidomaci;

import java.util.Scanner;

public class AproksimatorAlkohola {

		static void pica() {
			System.out.println("1.\t Rakija");
			System.out.println("2.\t Vinjak");
			System.out.println("3.\t Pivo");
			System.out.println("4.\t Vino");
		}
		public static void main (String[] args) {
			Scanner ulaz = new Scanner(System.in);
			System.out.println("Dobrodosli u alko aproksimator 9000!");
			System.out.println("Unesite vasu tezinu (kg):");
			
			double tezina = ulaz.nextDouble();
			int pice=0;
			int kolicina=0;
			double suma=0;
			double bac=0;
			String kazna="";
			int bodovi=0;
			int zabrana=0;
			String alkoholisanost="";
			System.out.println("Unesite vas pol (ako ste musko unesite 1, ako ste zensko unesite 0):");

			int pol=ulaz.nextInt();

			do {
				pica();
				System.out.println("Unesite id pica koje ste pili, ili -1 ako ste popili sva pica: ");
				pice=ulaz.nextInt();
				
				if(pice !=1 && pice !=2 && pice !=3 && pice !=4 && pice !=-1) {
					System.out.println("Greska! Zeljeni id nije u bazi podataka! Pokusajte ponovo:");
					pice=ulaz.nextInt();
				}

				if(pice !=-1) {
					System.out.println("Unesite koliko mililitara (ml) tog pica ste popili:");
					kolicina=ulaz.nextInt();
					suma+=volumen(pice,kolicina);
				}

			} 
			while(pice !=-1);
			if(suma>0) {
				bac=aproksimator(pol,tezina,suma);
				if(bac<0.20) {
					kazna="Bez kazne";
					bodovi=0;
					zabrana=0;
					alkoholisanost="Dozvoljena alkoholisanost";
					
				} else if(bac>0.20 && bac<0.51) {
					kazna="10000 din";
					bodovi=0;
					zabrana=0;
					alkoholisanost="Umerena alkoholisanost";

				} else if(bac>0.50 && bac < 0.81) {
					kazna="10000-20000 din";
					bodovi=6;
					zabrana=3;
					alkoholisanost="Srednja alkoholisanost";

				} else if(bac > 0.80 && bac < 1.21) {
					kazna="20000-40000 din";
					bodovi=8;
					zabrana=4;
					alkoholisanost="Visoka alkoholisanost";

				} else if(bac > 1.20 && bac < 1.61) {
					kazna="100000-120000 din";
					bodovi=9;
					zabrana=8;
					alkoholisanost="Teska alkoholisanost";

				} else if(bac > 1.60 && bac <=2.0) {
					kazna="100000-120000 din";
					bodovi=14;
					zabrana=8;
					alkoholisanost="Visoka alkoholisanost";

				} else {
					kazna="30-60 dana zatvora";
					bodovi=15;
					zabrana=9;
					alkoholisanost="Potpuna alkoholisanost";

				}

			}
			System.out.println("-------------------------------");
			System.out.printf("BAC: %.2f - %s",bac,alkoholisanost);
			System.out.println("\n-------------------------------");
			System.out.println("");
			System.out.println("Kazna: "+kazna);
			System.out.println("Kazneni poeni: "+bodovi);
			System.out.println("Zabrana voznje: "+ zabrana+" meseci");
		}
		static double volumen(int pice, int kolicina) {
			double volumen=0;
			double pa=0;
			
			switch(pice) {
			case 1:
				pa=0.5;
				break;

			case 2:
				pa=0.4;
				break;

			case 3:
				pa=0.047;
				break;

			case 4:
				pa=0.11;
				break;

			default:
				break;
			}
			if(pa>0) {
				volumen=kolicina*pa;
			}
			return volumen;
		}
		static double aproksimator(int pol, double tezina, double suma) {
			double bac=0;
			double r=0;
			double tezina2= tezina*1000;
			if(pol ==1) {
				r=0.55;
			} else {
				r=0.65;
			}
			bac=(suma/(tezina2*r))*1000;
			return bac;

	}

}
