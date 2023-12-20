pub fn reverse(input: &str) -> String {
   // todo!("Write a function to reverse {input}");
   input.chars().rev().collect()
}


// //new version
// use unicode_segmentation::UnicodeSegmentation;
// pub fn reverse(input: &str) -> String {
//     input.graphemes(true).rev().collect()
// }
