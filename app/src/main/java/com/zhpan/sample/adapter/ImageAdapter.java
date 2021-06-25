package com.zhpan.sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.zhpan.library.view.CornerImageView;
import com.zhpan.sample.R;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangpan
 * @date 2021/6/24
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.TestViewHolder> {

  @NonNull @NotNull @Override
  public TestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
    return new TestViewHolder(itemView);
  }

  @Override public void onBindViewHolder(@NonNull @NotNull TestViewHolder holder, int position) {
    holder.imageView.setBackgroundColor(
        holder.itemView.getContext()
            .getResources()
            .getColor(position % 2 == 0 ? R.color.red_500
                : R.color.green_500));
  }

  @Override public int getItemCount() {
    return 20;
  }

  public static class TestViewHolder extends RecyclerView.ViewHolder {
    CornerImageView imageView;

    public TestViewHolder(
        @NonNull @NotNull View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.image_view);
      imageView.setRoundCorner(
          itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.dp_10));
    }
  }
}
