import java.util.ArrayList;

class BoyerMoore{
	
	private int CHAR_MAX = 256;

	public ArrayList boyer_moore_horspool(char[] needle, char[] haystack){//algoritmen er tatt  fra forelesningen.
		//og har blitt endret i forhold til oppgaven 
		if ( needle.length > haystack.length ){ return null; }
			ArrayList<Integer> matches = new ArrayList<>();
			int[] bad_shift = new int[CHAR_MAX]; // 256
			
			for(int i = 0; i < CHAR_MAX; i++){
				bad_shift[i] = needle.length;
			}

		int offset = 0, scan = 0;
		int last = needle.length - 1;
		int maxoffset = haystack.length - needle.length;

		for(int i = 0; i < last; i++){
				bad_shift[needle[i]] = last - i;
			}

		//set forskjellig offset naar '_' er inkludert og finn siste wildcard
		for (int j = last-1;j>=0 ;j--) {
			
			if (needle[j] == '_') {
				
				for(int i = 0; i < CHAR_MAX; i++){
					
					if(bad_shift[i] > last - j){
						bad_shift[i] = last - j;
					}
				}
				break;
			}
		}
		
			while(offset <= maxoffset){
				//stemmer hvis needle[] = haystack || needle[] == '_'(wildcard)
				for(scan = last; (scan > -1 && (scan+offset) > -1) && (needle[scan] == haystack[scan+offset] || needle[scan]=='_'); scan--){
					
					if(scan == 0){ // match found!

					matches.add(offset);
					}
				}
		offset += bad_shift[haystack[offset + last]];
		}
		if (matches.size() > 0) {
			return matches;
		}
		return null;
	}
}